package jpize.util.net.tcp;

import jpize.util.Utils;
import jpize.util.io.DataStreamWriter;
import jpize.util.io.ExtDataInputStream;
import jpize.util.net.packet.NetPacket;
import jpize.util.security.AESKey;

import javax.crypto.Cipher;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class TCPClient {

    private Consumer<TCPConnection> onConnect, onDisconnect;
    private TCPListener onReceive;
    private TCPConnection.Factory connectionFactory;
    private TCPConnection connection;
    private Selector selector;

    public TCPClient() {
        this.setConnectionType(TCPConnection.CONNECTION_TYPE);
    }

    public TCPConnection connection() {
        return connection;
    }


    public TCPClient setConnectionType(Class<?> tcpConnectionClass) {
        this.connectionFactory = TCPConnection.getFactory(tcpConnectionClass);
        return this;
    }

    public TCPClient setConnectionType(TCPConnectionType type) {
        this.connectionFactory = TCPConnection.getFactory(type);
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

    public TCPClient setOnReceiveStream(TCPListenerStream onReceive) {
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


    private void createConnection(SocketChannel channel) throws IOException {
        final SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

        connection = connectionFactory.create(channel, key, onDisconnect);
        if(onConnect != null)
            onConnect.accept(connection);

        this.startReceiveThread();
    }

    public TCPClient connect(SocketAddress socketAddress, long timeoutMillis) {
        if(this.isConnected())
            throw new IllegalStateException("TCP client is already connected");

        try{
            // channel
            final SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            final boolean connectedInstantly = channel.connect(socketAddress);

            // create selector
            if(selector != null)
                Utils.close(selector);
            selector = Selector.open();

            if(connectedInstantly) {
                this.createConnection(channel);
                return this;
            }

            // wait for connection
            channel.register(selector, SelectionKey.OP_CONNECT);
            if(selector.select(timeoutMillis) == 0) {
                channel.close();
                throw new TimeoutException("Connection timed out");
            }

            // get key and connect
            final Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            final SelectionKey connectKey = keyIterator.next();
            keyIterator.remove();

            if(connectKey.isConnectable() && channel.finishConnect()) {
                this.createConnection(channel);
            }else{
                throw new ConnectException("Connection failed");
            }
        }catch(Exception e){
            Utils.close(selector);
            throw new RuntimeException("Failed to connect TCP client: " + e.getMessage());
        }
        return this;
    }

    public TCPClient connect(SocketAddress socketAddress) {
        return this.connect(socketAddress, 0L);
    }

    public TCPClient connect(String host, int port, long timeoutMillis) {
        return this.connect(new InetSocketAddress(host, port), timeoutMillis);
    }

    public TCPClient connect(String host, int port) {
        return this.connect(host, port, 0L);
    }


    private void startReceiveThread() {
        final Thread selectorThread = new Thread(() -> {
            while(!Thread.interrupted() && !this.isClosed())
                this.selectKeys();
        }, "TCP client thread #" + this.hashCode());
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
            final byte[] bytes = connection.read();
            if(bytes != null && onReceive != null)
                onReceive.receive(connection, bytes);
        }
        if(key.isValid() && key.isWritable()){
            connection.processWriteQueue(key);
        }
    }


    public boolean isConnected() {
        return (connection != null && connection.isConnected());
    }

    public boolean isClosed() {
        return (connection == null || connection.isClosed());
    }

    public TCPClient disconnect() {
        if(this.isConnected())
            connection.close();
        return this;
    }


    public TCPClient encodeOutput(Cipher encryptCipher) {
        if(this.isConnected())
            connection.encodeOutput(encryptCipher);
        return this;
    }

    public TCPClient encodeInput(Cipher decryptCipher) {
        if(this.isConnected())
            connection.encodeInput(decryptCipher);
        return this;
    }

    public TCPClient encode(Cipher encryptCipher, Cipher decryptCipher) {
        if(this.isConnected())
            connection.encode(encryptCipher, decryptCipher);
        return this;
    }

    public TCPClient encode(AESKey encodeKey) {
        if(this.isConnected())
            connection.encode(encodeKey);
        return this;
    }


    public boolean send(byte[] bytes) {
        if(this.isConnected())
            return connection.send(bytes);
        return false;
    }

    public boolean send(DataStreamWriter streamWriter) {
        if(this.isConnected())
            return connection.send(streamWriter);
        return false;
    }

    public boolean send(NetPacket<?> packet) {
        if(this.isConnected())
            return connection.send(packet);
        return false;
    }

}
