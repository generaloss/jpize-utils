package jpize.util.net.tcp;

import jpize.util.io.DataStreamWriter;
import jpize.util.io.ExtDataInputStream;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.Utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
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
        this.setConnectionType(BufferedTCPConnection.class);
        this.connections = new CopyOnWriteArrayList<>();
        this.disconnector = (connection) -> {
            if(onDisconnect != null) onDisconnect.accept(connection);
            connections.remove(connection);
        };
    }


    public TCPServer setConnectionType(Type tcpConnectionClass) {
        this.connectionFactory = TCPConnection.getFactory(tcpConnectionClass);
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
            this.startSelectorThread();

        }catch(Exception e){
            throw new IllegalStateException("TCP server failed to start: " + e.getMessage());
        }

        return this;
    }

    public TCPServer run(String host, int port) {
        return run(new InetSocketAddress(host, port));
    }

    public TCPServer run(int port) {
        return run(new InetSocketAddress(port));
    }

    private void startSelectorThread() {
        final Thread selectorThread = new Thread(() -> {
            try{
                while(!Thread.interrupted()){
                    this.selectKeys();
                    Thread.yield();
                }
            }catch(IOException e){
                throw new RuntimeException(e); //!ignored
            }
        }, "TCP-server Thread #" + this.hashCode());

        selectorThread.setPriority(8);
        selectorThread.setDaemon(true);
        selectorThread.start();
    }

    private synchronized void selectKeys() throws IOException {
        selector.select();

        for(SelectionKey key: selector.selectedKeys()){
            if(!key.isValid())
                continue;

            if(key.isReadable()){
                this.receiveBytes((TCPConnection) key.attachment());
            }else if(key.isAcceptable()){
                this.acceptNewConnection();
            }
        }
    }

    private void receiveBytes(TCPConnection connection) {
        final byte[] bytes = connection.read();
        if(bytes != null && onReceive != null)
            onReceive.receive(connection, bytes);
    }

    private void acceptNewConnection() throws IOException {
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
        return this;
    }


    public void broadcast(byte[] bytes) {
        for(TCPConnection connection: connections)
            connection.send(bytes);
    }

    public void broadcast(TCPConnection except, byte[] bytes) {
        for(TCPConnection connection: connections)
            if(connection != except)
                connection.send(bytes);
    }

    public void broadcast(DataStreamWriter streamWriter) {
        this.broadcast(DataStreamWriter.writeBytes(streamWriter));
    }

    public void broadcast(TCPConnection except, DataStreamWriter streamWriter) {
        this.broadcast(except, DataStreamWriter.writeBytes(streamWriter));
    }

    public void broadcast(IPacket<?> packet) {
        this.broadcast(stream -> {
            stream.writeShort(packet.getPacketID());
            packet.write(stream);
        });
    }

    public void broadcast(TCPConnection except, IPacket<?> packet) {
        this.broadcast(except, stream -> {
            stream.writeShort(packet.getPacketID());
            packet.write(stream);
        });
    }

}
