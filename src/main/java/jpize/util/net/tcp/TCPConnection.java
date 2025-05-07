package jpize.util.net.tcp;

import jpize.util.Utils;
import jpize.util.io.DataStreamWriter;
import jpize.util.net.packet.NetPacket;
import jpize.util.security.AESKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class TCPConnection implements Closeable {

    public static TCPConnectionType CONNECTION_TYPE = TCPConnectionType.PACKET;

    protected final SocketChannel channel;
    protected final SelectionKey selectionKey;
    protected final TCPCloseable onClose;
    protected final TCPSocketOptions options;
    private final Queue<ByteBuffer> writeQueue;
    private Cipher encryptCipher, decryptCipher;
    private Object attachment;

    public TCPConnection(SocketChannel channel, SelectionKey selectionKey, TCPCloseable onClose) {
        this.channel = channel;
        this.selectionKey = selectionKey;
        this.onClose = onClose;
        this.options = new TCPSocketOptions(channel.socket());
        this.writeQueue = new ConcurrentLinkedQueue<>();
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
        if(encryptCipher == null)
            return bytes;
        try{
            return encryptCipher.doFinal(bytes);
        }catch(IllegalBlockSizeException | BadPaddingException e){
            throw new IllegalStateException("Encryption error: " + e.getMessage());
        }
    }

    protected byte[] tryToDecryptBytes(byte[] bytes) {
        if(decryptCipher == null)
            return bytes;
        try{
            return decryptCipher.doFinal(bytes);
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


    protected boolean toWriteQueue(ByteBuffer buffer) {
        try{
            synchronized(writeQueue) {
                if(writeQueue.isEmpty())
                    channel.write(buffer);

                if(buffer.hasRemaining()) {
                    writeQueue.add(buffer);
                    selectionKey.interestOpsOr(SelectionKey.OP_WRITE);
                    selectionKey.selector().wakeup();
                }
            }
            return true;
        }catch(IOException e){
            this.close(e);
            return false;
        }
    }

    protected void processWriteQueue(SelectionKey key) {
        try{
            synchronized(writeQueue) {
                while(!writeQueue.isEmpty()) {
                    final ByteBuffer buffer = writeQueue.peek();
                    channel.write(buffer);

                    if(buffer.hasRemaining())
                        return;
                    writeQueue.poll();
                }
                key.interestOps(SelectionKey.OP_READ);
            }
        }catch(Exception e){
            this.close(e);
        }
    }

    public int getWriteQueueSize() {
        return writeQueue.size();
    }


    protected void close(String message) {
        if(this.isClosed())
            return;

        if(onClose != null)
            onClose.close(this, message);

        Utils.close(channel);
    }

    protected void close(Exception e) {
        this.close("Error occured. Close connection: " + e.getMessage());
    }

    @Override
    public void close() {
        this.close("Connection closed");
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
        TCPConnection create(SocketChannel channel, SelectionKey selectionKey, TCPCloseable onClose);
    }

    private static final Map<Class<?>, Factory> FACTORY_BY_CLASS = new HashMap<>();

    public static void registerFactory(Class<?> connectionClass, Factory factory) {
        FACTORY_BY_CLASS.put(connectionClass, factory);
    }

    static {
        registerFactory(PacketTCPConnection.class, PacketTCPConnection::new);
        registerFactory(NativeTCPConnection.class, NativeTCPConnection::new);
    }

    public static Factory getFactory(Class<?> connectionClass) {
        if(!FACTORY_BY_CLASS.containsKey(connectionClass))
            throw new Error("Class '" + connectionClass + "' is not registered as a TCP connection factory");
        return FACTORY_BY_CLASS.get(connectionClass);
    }

    public static Factory getFactory(TCPConnectionType type) {
        return getFactory(type.getConnectionClass());
    }

    public static TCPConnection create(Class<?> connectionClass, SocketChannel channel, SelectionKey selectionKey, TCPSocketOptions options, TCPCloseable onClose) {
        final Factory factory = getFactory(connectionClass);
        return factory.create(channel, selectionKey, onClose);
    }

}
