package jpize.util.net.tcp;

import jpize.util.io.DataStreamWriter;
import jpize.util.io.ExtDataInputStream;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.security.AESKey;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

public class TCPClient {

    private Consumer<TCPConnection> onConnect, onDisconnect;
    private TCPListener onReceive;
    private TCPConnection.Factory connectionFactory;
    private TCPConnection connection;
    private Selector selector;

    public TCPClient() {
        this.setConnectionType(BufferedTCPConnection.class);
    }

    public TCPConnection connection() {
        return connection;
    }


    public TCPClient setConnectionType(Type tcpConnectionClass) {
        this.connectionFactory = TCPConnection.getFactory(tcpConnectionClass);
        return this;
    }

    public TCPClient setOnConnect(Consumer<TCPConnection> onConnect) {
        this.onConnect = onConnect;
        return this;
    }

    public TCPClient setOnDisconnect(Consumer<TCPConnection> onDisconnect) {
        this.onDisconnect = onDisconnect;
        return this;
    }

    public TCPClient setOnReceive(TCPListener onReceive) {
        this.onReceive = onReceive;
        return this;
    }

    public TCPClient setOnReceiveStream(TCPStreamListener onReceive) {
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


    public TCPClient connect(SocketAddress socketAddress) {
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
        }catch(IOException e){
            throw new RuntimeException(e); //!ignored
        }
        return this;
    }

    public TCPClient connect(String host, int port) {
        return connect(new InetSocketAddress(host, port));
    }


    private void startReceiveThread() {
        final Thread selectorThread = new Thread(() -> {
            try{
                while(!Thread.interrupted() && !this.isClosed()){
                    selector.select();
                    this.receiveBytes();
                }
            }catch(IOException e){
                throw new RuntimeException(e); //!ignored
            }
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


    public boolean isConnected() {
        return (connection != null && connection.isConnected());
    }

    public boolean isClosed(){
        return (connection == null || connection.isClosed());
    }

    public TCPClient disconnect() {
        if(this.isConnected())
            connection.close();
        return this;
    }


    public TCPClient encode(AESKey encodeKey) {
        if(this.isConnected())
            connection.encode(encodeKey);
        return this;
    }


    public TCPClient send(byte[] bytes) {
        if(this.isConnected())
            connection.send(bytes);
        return this;
    }

    public TCPClient send(DataStreamWriter streamWriter) {
        if(this.isConnected())
            connection.send(streamWriter);
        return this;
    }

    public TCPClient send(IPacket<?> packet) {
        if(this.isConnected())
            connection.send(packet);
        return this;
    }

}
