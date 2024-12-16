package jpize.util.net.tcp;

import jpize.util.Utils;
import jpize.util.function.IOConsumer;
import jpize.util.io.ExtDataOutputStream;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.security.AESKey;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class TCPConnection implements Closeable {

    protected final SocketChannel channel;
    protected final SelectionKey selectionKey;
    protected final Consumer<TCPConnection> onDisconnect;
    private AESKey encodeKey;
    private Object attachment;

    public TCPConnection(SocketChannel channel, SelectionKey selectionKey, Consumer<TCPConnection> onDisconnect) {
        this.channel = channel;
        this.selectionKey = selectionKey;
        this.onDisconnect = onDisconnect;
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

    public <O> O attachment() {
        // noinspection unchecked
        return (O) attachment;
    }

    public void attach(Object attachment) {
        this.attachment = attachment;
    }



    public void encode(AESKey encodeKey) {
        this.encodeKey = encodeKey;
    }

    protected byte[] tryToEncryptBytes(byte[] bytes) {
        return (encodeKey == null) ? bytes : encodeKey.encrypt(bytes);
    }

    protected byte[] tryToDecryptBytes(byte[] bytes) {
        return (encodeKey == null) ? bytes : encodeKey.decrypt(bytes);
    }


    protected abstract byte[] read();

    public abstract void send(byte[] bytes);

    public void send(ByteArrayOutputStream byteStream) {
        this.send(byteStream.toByteArray());
    }

    public void send(IOConsumer<ExtDataOutputStream> streamConsumer) {
        try{
            final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            final ExtDataOutputStream dataStream = new ExtDataOutputStream(byteStream);
            streamConsumer.accept(dataStream);
            this.send(byteStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void send(IPacket<?> packet) {
        this.send(dataStream -> {
            dataStream.writeShort(packet.getPacketID());
            packet.write(dataStream);
        });
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


    public boolean getTcpNoDelay() {
        try{ return this.socket().getTcpNoDelay(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSoTimeout() {
        try{ return this.socket().getSoTimeout(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getKeepAlive() {
        try{ return this.socket().getKeepAlive(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSendBufferSize() {
        try{ return this.socket().getSendBufferSize(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getReceiveBufferSize() {
        try{ return this.socket().getReceiveBufferSize(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getTrafficClass() {
        try{ return this.socket().getTrafficClass(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getReuseAddress() {
        try{ return this.socket().getReuseAddress(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getOOBInline() {
        try{ return this.socket().getOOBInline(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSoLinger() {
        try{ return this.socket().getSoLinger(); }catch(SocketException e){ throw new RuntimeException(e); }
    }


    public void setTcpNoDelay(boolean on) {
        try{ this.socket().setTcpNoDelay(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSoTimeout(int timeout) {
        try{ this.socket().setSoTimeout(timeout); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setKeepAlive(boolean on) {
        try{ this.socket().setKeepAlive(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSendBufferSize(int size) {
        try{ this.socket().setSendBufferSize(size); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setReceiveBufferSize(int size) {
        try{ this.socket().setReceiveBufferSize(size); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setTrafficClass(int trafficClass) {
        try{ this.socket().setTrafficClass(trafficClass); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setReuseAddress(boolean on) {
        try{ this.socket().setReuseAddress(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setOOBInline(boolean on) {
        try{ this.socket().setOOBInline(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSoLinger(boolean on, int linger) {
        try{ this.socket().setSoLinger(on, linger); }catch(SocketException e){ throw new RuntimeException(e); }
    }


    public interface Factory {
        TCPConnection create(SocketChannel channel, SelectionKey selectionKey, Consumer<TCPConnection> onDisconnect);
    }

    private static final Map<Type, Factory> FACTORY_BY_CLASS = new HashMap<>(){{{
        put(BufferedTCPConnection.class, BufferedTCPConnection::new);
        put(NativeTCPConnection.class, NativeTCPConnection::new);
    }}};

    public static void registerFactory(Type connectionClass, Factory factory) {
        FACTORY_BY_CLASS.put(connectionClass, factory);
    }

    public static Factory getFactory(Type connectionClass) {
        if(!FACTORY_BY_CLASS.containsKey(connectionClass))
            throw new Error("Class '" + connectionClass + "' is not registered as a TCP connection factory.");
        return FACTORY_BY_CLASS.get(connectionClass);
    }

    public static TCPConnection create(Type connectionClass, SocketChannel channel, SelectionKey selectionKey, Consumer<TCPConnection> onDisconnect) {
        final Factory factory = getFactory(connectionClass);
        return factory.create(channel, selectionKey, onDisconnect);
    }

}
