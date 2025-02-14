package jpize.util.net.tcp;

import jpize.util.Utils;
import jpize.util.io.DataStreamWriter;
import jpize.util.net.packet.NetPacket;
import jpize.util.security.AESKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.Closeable;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public abstract class TCPConnection implements Closeable {

    public static TCPConnectionType CONNECTION_TYPE = TCPConnectionType.PACKET;

    protected final SocketChannel channel;
    protected final SelectionKey selectionKey;
    protected final Consumer<TCPConnection> onDisconnect;
    protected final Queue<ByteBuffer> writeQueue;
    protected final TCPSocketOptions options;
    private Cipher encryptCipher, decryptCipher;
    private Object attachment;

    public TCPConnection(SocketChannel channel, SelectionKey selectionKey, Consumer<TCPConnection> onDisconnect) {
        this.channel = channel;
        this.selectionKey = selectionKey;
        this.onDisconnect = onDisconnect;
        this.writeQueue = new ConcurrentLinkedQueue<>();
        this.options = new TCPSocketOptions(channel.socket());
    }

    public SocketChannel channel() {
        return channel;
    }

    public Socket socket() {
        return channel.socket();
    }

    public SelectionKey selectionKey() {
        return selectionKey;
    }

    public TCPSocketOptions options() {
        return options;
    }

    @SuppressWarnings("unchecked")
    public <O> O attachment() {
        return (O) attachment;
    }

    public void attach(Object attachment) {
        this.attachment = attachment;
    }



    public void encodeOutput(Cipher encryptCipher) {
        this.encryptCipher = encryptCipher;
    }

    public void encodeInput(Cipher decryptCipher) {
        this.decryptCipher = decryptCipher;
    }

    public void encode(Cipher encryptCipher, Cipher decryptCipher) {
        this.encodeOutput(encryptCipher);
        this.encodeInput(decryptCipher);
    }

    public void encode(AESKey encodeKey) {
        if(encodeKey == null) {
            this.encode(null, null);
        }else{
            this.encode(encodeKey.getEncryptCipher(), encodeKey.getDecryptCipher());
        }
    }

    protected byte[] tryToEncryptBytes(byte[] bytes) {
        try{
            return (encryptCipher == null) ? bytes : encryptCipher.doFinal(bytes);
        }catch(IllegalBlockSizeException | BadPaddingException e){
            throw new IllegalStateException("Encryption error: " + e.getMessage());
        }
    }

    protected byte[] tryToDecryptBytes(byte[] bytes) {
        try{
            return (decryptCipher == null) ? bytes : decryptCipher.doFinal(bytes);
        }catch(IllegalBlockSizeException | BadPaddingException e){
            throw new IllegalStateException("Decryption error: " + e.getMessage());
        }
    }


    protected abstract byte[] read();

    public abstract boolean send(byte[] bytes);

    public boolean send(DataStreamWriter streamWriter) {
        return this.send(DataStreamWriter.writeBytes(streamWriter));
    }

    public boolean send(NetPacket<?> packet) {
        return this.send(stream -> {
            stream.writeShort(packet.getPacketID());
            packet.write(stream);
        });
    }

    protected void processWriteQueue(SelectionKey key) {
        try{
            while(!writeQueue.isEmpty()){
                final ByteBuffer buffer = writeQueue.peek();
                channel.write(buffer);
                if(buffer.hasRemaining()){
                    return;
                }else{
                    writeQueue.poll();
                }
            }
            key.interestOps(SelectionKey.OP_READ);
        }catch(Exception ignored){
            this.close();
        }
    }

    public int getWriteQueueSize() {
        return writeQueue.size();
    }


    @Override
    public void close() {
        if(this.isClosed()) return;
        if(onDisconnect != null) onDisconnect.accept(this);
        Utils.close(channel);
    }


    public boolean isConnected() {
        return this.socket().isConnected();
    }

    public boolean isClosed() {
        return this.socket().isClosed();
    }

    public int getPort() {
        return this.socket().getPort();
    }

    public int getLocalPort() {
        return this.socket().getLocalPort();
    }

    public InetAddress getAddress() {
        return this.socket().getInetAddress();
    }

    public InetAddress getLocalAddress() {
        return this.socket().getLocalAddress();
    }


    public interface Factory {
        TCPConnection create(SocketChannel channel, SelectionKey selectionKey, Consumer<TCPConnection> onDisconnect);
    }

    private static final Map<Class<?>, Factory> FACTORY_BY_CLASS = new HashMap<>(){{{
        this.put(PacketTCPConnection.class, PacketTCPConnection::new);
        this.put(NativeTCPConnection.class, NativeTCPConnection::new);
    }}};

    public static void registerFactory(Class<?> connectionClass, Factory factory) {
        FACTORY_BY_CLASS.put(connectionClass, factory);
    }

    public static Factory getFactory(Class<?> connectionClass) {
        if(!FACTORY_BY_CLASS.containsKey(connectionClass))
            throw new Error("Class '" + connectionClass + "' is not registered as a TCP connection factory");
        return FACTORY_BY_CLASS.get(connectionClass);
    }

    public static Factory getFactory(TCPConnectionType type) {
        return getFactory(type.getConnectionClass());
    }

    public static TCPConnection create(Class<?> connectionClass, SocketChannel channel, SelectionKey selectionKey, TCPSocketOptions options, Consumer<TCPConnection> onDisconnect) {
        final Factory factory = getFactory(connectionClass);
        return factory.create(channel, selectionKey, onDisconnect);
    }

}
