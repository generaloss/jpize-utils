package jpize.util.net.tcp;

import jpize.util.io.DataStreamWriter;
import jpize.util.io.ExtDataInputStream;
import jpize.util.net.tcp.packet.NetPacket;
import jpize.util.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.*;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class TCPServer {

    private Consumer<TCPConnection> onConnect, onDisconnect;
    private TCPListener onReceive;
    private TCPConnection.Factory connectionFactory;
    private final CopyOnWriteArrayList<TCPConnection> connections;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final Consumer<TCPConnection> disconnector;

    public TCPServer() {
        this.setConnectionType(TCPConnection.DEFAULT_TYPE);
        this.connections = new CopyOnWriteArrayList<>();
        this.disconnector = (connection) -> {
            connections.remove(connection);
            if(onDisconnect != null)
                onDisconnect.accept(connection);
        };
    }


    public TCPServer setConnectionType(Class<?> tcpConnectionClass) {
        this.connectionFactory = TCPConnection.getFactory(tcpConnectionClass);
        return this;
    }

    public TCPServer setConnectionType(TCPConnectionType type) {
        this.connectionFactory = TCPConnection.getFactory(type);
        return this;
    }


    public TCPServer setOnConnect(Consumer<TCPConnection> onConnect) {
        this.onConnect = onConnect;
        return this;
    }

    public TCPServer setOnDisconnect(Consumer<TCPConnection> onDisconnect) {
        this.onDisconnect = onDisconnect;
        return this;
    }

    public TCPServer setOnReceive(TCPListener onReceive) {
        this.onReceive = onReceive;
        return this;
    }

    public TCPServer setOnReceiveStream(TCPStreamListener onReceive) {
        this.onReceive = (sender, bytes) -> {
            try{
                final ExtDataInputStream stream = new ExtDataInputStream(bytes);
                onReceive.receive(sender, stream);
                stream.close();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        };
        return this;
    }


    public TCPServer run(SocketAddress address) {
        if(this.isAlive())
            throw new IllegalStateException("TCP server is already running");

        try{
            connections.clear();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(address);
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            this.startThread();

        }catch(Exception e){
            throw new IllegalStateException("Failed to start TCP server: " + e.getMessage());
        }

        return this;
    }

    public TCPServer run(String host, int port) {
        return this.run(new InetSocketAddress(host, port));
    }

    public TCPServer run(int port) {
        return this.run(new InetSocketAddress(port));
    }

    private void startThread() {
        final Thread selectorThread = new Thread(() -> {
            while(!Thread.interrupted() && !this.isClosed())
                this.selectKeys();
        }, "TCP server thread #" + this.hashCode());
        selectorThread.setDaemon(true);
        selectorThread.start();
    }

    private void selectKeys() {
        try{
            selector.select();
            final Set<SelectionKey> selectedKeys = selector.selectedKeys();
            for(SelectionKey key : selectedKeys)
                this.processKey(key);
            selectedKeys.clear();

        }catch(Exception ignored) { }
    }

    private void processKey(SelectionKey key) {
        if(key.isValid() && key.isReadable()){
            final TCPConnection connection = ((TCPConnection) key.attachment());
            final byte[] bytes = connection.read();
            if(bytes != null && onReceive != null)
                onReceive.receive(connection, bytes);
        }
        if(key.isValid() && key.isWritable()){
            final TCPConnection connection = ((TCPConnection) key.attachment());
            connection.processWriteQueue(key);
        }
        if(key.isValid() && key.isAcceptable()){
            this.acceptNewConnection();
        }
    }

    private void acceptNewConnection() {
        try{
            final SocketChannel channel = serverSocketChannel.accept();
            if(channel == null)
                return;

            channel.configureBlocking(false);
            final SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

            final TCPConnection connection = connectionFactory.create(channel, key, disconnector);
            connections.add(connection);
            key.attach(connection);

            if(onConnect != null)
                onConnect.accept(connection);
        }catch(IOException ignored){ }
    }


    public Collection<TCPConnection> getConnections() {
        return connections;
    }

    public boolean isAlive() {
        return (serverSocketChannel != null && !serverSocketChannel.socket().isClosed());
    }

    public boolean isClosed() {
        return (serverSocketChannel == null || serverSocketChannel.socket().isClosed());
    }

    public TCPServer close() {
        if(this.isClosed())
            return this;

        for(TCPConnection connection: connections)
            connection.close();
        connections.clear();

        Utils.close(serverSocketChannel);
        Utils.close(selector);
        return this;
    }


    public boolean broadcast(byte[] bytes) {
        boolean result = false;
        for(TCPConnection connection: connections)
            result |= connection.send(bytes);
        return result;
    }

    public boolean broadcast(TCPConnection except, byte[] bytes) {
        boolean result = false;
        for(TCPConnection connection: connections)
            if(connection != except)
                result |= connection.send(bytes);
        return result;
    }

    public boolean broadcast(DataStreamWriter streamWriter) {
        return this.broadcast(DataStreamWriter.writeBytes(streamWriter));
    }

    public boolean broadcast(TCPConnection except, DataStreamWriter streamWriter) {
        return this.broadcast(except, DataStreamWriter.writeBytes(streamWriter));
    }

    public boolean broadcast(NetPacket<?> packet) {
        return this.broadcast(stream -> {
            stream.writeShort(packet.getPacketID());
            packet.write(stream);
        });
    }

    public boolean broadcast(TCPConnection except, NetPacket<?> packet) {
        return this.broadcast(except, stream -> {
            stream.writeShort(packet.getPacketID());
            packet.write(stream);
        });
    }

}
