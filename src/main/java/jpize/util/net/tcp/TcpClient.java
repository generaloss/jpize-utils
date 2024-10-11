package jpize.util.net.tcp;

import jpize.util.function.IOConsumer;
import jpize.util.io.ExtDataInputStream;
import jpize.util.io.ExtDataOutputStream;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.security.KeyAES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

public class TcpClient {

    private Consumer<TcpConnection> onConnect, onDisconnect;
    private TcpListener onReceive;
    private TcpConnection.Factory connectionFactory;
    private TcpConnection connection;
    private Selector selector;

    public TcpClient() {
        this.setConnectionType(BufferedTcpConnection.class);
    }


    public TcpClient setConnectionType(Type tcpConnectionClass) {
        this.connectionFactory = TcpConnection.getFactory(tcpConnectionClass);
        return this;
    }

    public TcpClient setOnConnect(Consumer<TcpConnection> onConnect) {
        this.onConnect = onConnect;
        return this;
    }

    public TcpClient setOnDisconnect(Consumer<TcpConnection> onDisconnect) {
        this.onDisconnect = onDisconnect;
        return this;
    }

    public TcpClient setOnReceive(TcpListener onReceive) {
        this.onReceive = onReceive;
        return this;
    }

    public TcpClient setOnReceiveStream(TcpStreamListener onReceive) {
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


    public TcpClient connect(SocketAddress socketAddress) {
        if(this.isConnected())
            throw new IllegalStateException("TCP-Client already connected.");

        try{
            final SocketChannel socket = SocketChannel.open();
            socket.connect(socketAddress);
            socket.configureBlocking(false);

            selector = Selector.open();
            final SelectionKey key = socket.register(selector, SelectionKey.OP_READ);

            connection = connectionFactory.create(socket, key, onDisconnect);
            if(onConnect != null)
                onConnect.accept(connection);

            this.startReceiveThread();
        }catch(IOException ignored){ }
        return this;
    }

    public TcpClient connect(String host, int port) {
        return connect(new InetSocketAddress(host, port));
    }


    private void startReceiveThread() {
        final Thread selectorThread = new Thread(() -> {
            try{
                while(!Thread.interrupted() && !this.isClosed()){
                    selector.select();
                    this.receiveBytes();
                }
            }catch(IOException ignored){ }
        }, "TCP-client Thread #" + this.hashCode());

        selectorThread.setPriority(7);
        selectorThread.setDaemon(true);
        selectorThread.start();
    }

    private void receiveBytes() {
        final byte[] bytes = connection.read();
        if(bytes != null && onReceive != null)
            onReceive.receive(connection, bytes);
    }


    public TcpConnection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return (connection != null && connection.isConnected());
    }

    public boolean isClosed(){
        return (connection == null || connection.isClosed());
    }

    public TcpClient disconnect() {
        if(this.isConnected())
            connection.close();
        return this;
    }


    public TcpClient encode(KeyAES encodeKey) {
        if(this.isConnected())
            connection.encode(encodeKey);
        return this;
    }


    public TcpClient send(byte[] bytes) {
        if(this.isConnected())
            connection.send(bytes);
        return this;
    }

    public TcpClient send(ByteArrayOutputStream stream) {
        if(this.isConnected())
            connection.send(stream);
        return this;
    }

    public TcpClient send(IOConsumer<ExtDataOutputStream> streamConsumer) {
        if(this.isConnected())
            connection.send(streamConsumer);
        return this;
    }

    public TcpClient send(IPacket<?> packet) {
        if(this.isConnected())
            connection.send(packet);
        return this;
    }

}
