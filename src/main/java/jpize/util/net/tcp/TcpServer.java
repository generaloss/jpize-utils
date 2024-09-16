package jpize.util.net.tcp;

import jpize.util.function.IOConsumer;
import jpize.util.io.ExtDataInputStream;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.Utils;
import jpize.util.io.ExtDataOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class TcpServer {

    private Consumer<TcpConnection> onConnect, onDisconnect;
    private TcpListener onReceive;
    private CopyOnWriteArrayList<TcpConnection> connections;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final Consumer<TcpConnection> disconnector = (connection) -> {
        if(onDisconnect != null)
            onDisconnect.accept(connection);
        connections.remove(connection);
    };


    public TcpServer setOnConnect(Consumer<TcpConnection> onConnect) {
        this.onConnect = onConnect;
        return this;
    }

    public TcpServer setOnDisconnect(Consumer<TcpConnection> onDisconnect) {
        this.onDisconnect = onDisconnect;
        return this;
    }

    public TcpServer setOnReceive(TcpListener onReceive) {
        this.onReceive = onReceive;
        return this;
    }

    public TcpServer setOnReceiveStream(TcpStreamListener onReceive) {
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


    public TcpServer run(SocketAddress address) {
        if(isAlive())
            throw new IllegalStateException("TCP-Server already running.");

        try{
            connections = new CopyOnWriteArrayList<>();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(address);
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            startSelectorThread();

        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return this;
    }

    public TcpServer run(String host, int port) {
        return run(new InetSocketAddress(host, port));
    }

    public TcpServer run(int port) {
        return run(new InetSocketAddress(port));
    }

    private void startSelectorThread() {
        final Thread selectorThread = new Thread(() -> {
            try{
                while(!Thread.interrupted())
                    selectKeys();
            }catch(IOException ignored){ }
        }, "TCP-server Thread #" + this.hashCode());

        selectorThread.setPriority(8);
        selectorThread.setDaemon(true);
        selectorThread.start();
    }

    private void selectKeys() throws IOException {
        selector.select();

        for(SelectionKey key: selector.selectedKeys()){
            if(key.isReadable())
                receiveBytes((TcpConnection) key.attachment());
            else if(key.isAcceptable())
                acceptNewConnection();
        }
    }

    private void receiveBytes(TcpConnection connection) {
        connection.readBytes(bytes -> {
            if(onReceive != null)
                onReceive.receive(connection, bytes);
        });
    }

    private void acceptNewConnection() throws IOException {
        final SocketChannel channel = serverSocketChannel.accept();
        if(channel == null)
            return;

        channel.configureBlocking(false);
        final SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

        final TcpConnection connection = new TcpConnection(channel, key, disconnector);
        connections.add(connection);
        key.attach(connection);

        if(onConnect != null)
            onConnect.accept(connection);
    }


    public Collection<TcpConnection> getConnections() {
        return connections;
    }

    public boolean isAlive() {
        return (serverSocketChannel != null && !serverSocketChannel.socket().isClosed());
    }

    public boolean isClosed() {
        return (serverSocketChannel == null || serverSocketChannel.socket().isClosed());
    }

    public TcpServer close() {
        if(isClosed())
            return this;

        for(TcpConnection connection: connections)
            connection.close();
        connections.clear();

        Utils.close(serverSocketChannel);
        return this;
    }


    public void broadcast(byte[] bytes) {
        for(TcpConnection connection: connections)
            connection.send(bytes);
    }

    public void broadcast(TcpConnection except, byte[] bytes) {
        for(TcpConnection connection: connections)
            if(connection != except)
                connection.send(bytes);
    }

    public void broadcast(ByteArrayOutputStream stream) {
        this.broadcast(stream.toByteArray());
    }

    public void broadcast(TcpConnection except, ByteArrayOutputStream stream) {
        this.broadcast(except, stream.toByteArray());
    }

    public void broadcast(IOConsumer<ExtDataOutputStream> streamConsumer) {
        try{
            final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            final ExtDataOutputStream dataStream = new ExtDataOutputStream(byteStream);
            streamConsumer.accept(dataStream);
            this.broadcast(byteStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void broadcast(TcpConnection except, IOConsumer<ExtDataOutputStream> streamConsumer) {
        try{
            final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            final ExtDataOutputStream dataStream = new ExtDataOutputStream(byteStream);
            streamConsumer.accept(dataStream);
            this.broadcast(except, byteStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void broadcast(IPacket<?> packet) {
        this.broadcast(dataStream -> {
            dataStream.writeShort(packet.getPacketID());
            packet.write(dataStream);
        });
    }

    public void broadcast(TcpConnection except, IPacket<?> packet) {
        this.broadcast(except, dataStream -> {
            dataStream.writeShort(packet.getPacketID());
            packet.write(dataStream);
        });
    }

}
