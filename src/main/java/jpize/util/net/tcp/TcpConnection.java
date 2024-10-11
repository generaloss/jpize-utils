package jpize.util.net.tcp;

import jpize.util.Utils;
import jpize.util.function.IOConsumer;
import jpize.util.io.ExtDataOutputStream;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.security.KeyAES;

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

public abstract class TcpConnection implements Closeable {

    protected final SocketChannel channel;
    protected final SelectionKey selectionKey;
    protected final Consumer<TcpConnection> onDisconnect;
    private KeyAES encodeKey;

    public TcpConnection(SocketChannel channel, SelectionKey selectionKey, Consumer<TcpConnection> onDisconnect) {
        this.channel = channel;
        this.selectionKey = selectionKey;
        this.onDisconnect = onDisconnect;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    public SelectionKey getSelectionKey() {
        return selectionKey;
    }


    public void encode(KeyAES encodeKey) {
        this.encodeKey = encodeKey;
    }

    protected byte[] tryToEncryptBytes(byte[] bytes) {
        if(encodeKey == null){ return bytes;
        }else return encodeKey.encrypt(bytes);
    }

    protected byte[] tryToDecryptBytes(byte[] bytes) {
        if(encodeKey == null){ return bytes;
        }else return encodeKey.decrypt(bytes);
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


    public Socket getSocket() {
        return channel.socket();
    }

    public boolean isConnected() {
        return this.getSocket().isConnected();
    }

    public boolean isClosed() {
        return this.getSocket().isClosed();
    }

    public int getPort() {
        return this.getSocket().getPort();
    }

    public int getLocalPort() {
        return this.getSocket().getLocalPort();
    }

    public InetAddress getAddress() {
        return this.getSocket().getInetAddress();
    }

    public InetAddress getLocalAddress() {
        return this.getSocket().getLocalAddress();
    }


    public boolean getTcpNoDelay() {
        try{ return this.getSocket().getTcpNoDelay(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSoTimeout() {
        try{ return this.getSocket().getSoTimeout(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getKeepAlive() {
        try{ return this.getSocket().getKeepAlive(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSendBufferSize() {
        try{ return this.getSocket().getSendBufferSize(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getReceiveBufferSize() {
        try{ return this.getSocket().getReceiveBufferSize(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getTrafficClass() {
        try{ return this.getSocket().getTrafficClass(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getReuseAddress() {
        try{ return this.getSocket().getReuseAddress(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getOOBInline() {
        try{ return this.getSocket().getOOBInline(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSoLinger() {
        try{ return this.getSocket().getSoLinger(); }catch(SocketException e){ throw new RuntimeException(e); }
    }


    public void setTcpNoDelay(boolean on) {
        try{ this.getSocket().setTcpNoDelay(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSoTimeout(int timeout) {
        try{ this.getSocket().setSoTimeout(timeout); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setKeepAlive(boolean on) {
        try{ this.getSocket().setKeepAlive(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSendBufferSize(int size) {
        try{ this.getSocket().setSendBufferSize(size); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setReceiveBufferSize(int size) {
        try{ this.getSocket().setReceiveBufferSize(size); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setTrafficClass(int trafficClass) {
        try{ this.getSocket().setTrafficClass(trafficClass); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setReuseAddress(boolean on) {
        try{ this.getSocket().setReuseAddress(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setOOBInline(boolean on) {
        try{ this.getSocket().setOOBInline(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSoLinger(boolean on, int linger) {
        try{ this.getSocket().setSoLinger(on, linger); }catch(SocketException e){ throw new RuntimeException(e); }
    }


    public interface Factory {
        TcpConnection create(SocketChannel channel, SelectionKey selectionKey, Consumer<TcpConnection> onDisconnect);
    }

    private static final Map<Type, Factory> FACTORY_BY_CLASS = new HashMap<>(){{{
        put(BufferedTcpConnection.class, BufferedTcpConnection::new);
        put(NativeTcpConnection.class, NativeTcpConnection::new);
    }}};

    public static void registerFactory(Type connectionClass, Factory factory) {
        FACTORY_BY_CLASS.put(connectionClass, factory);
    }

    public static Factory getFactory(Type connectionClass) {
        if(!FACTORY_BY_CLASS.containsKey(connectionClass))
            throw new Error("Class '" + connectionClass + "' is not registered as a TCP connection factory.");
        return FACTORY_BY_CLASS.get(connectionClass);
    }

    public static TcpConnection create(Type connectionClass, SocketChannel channel, SelectionKey selectionKey, Consumer<TcpConnection> onDisconnect) {
        final Factory factory = getFactory(connectionClass);
        return factory.create(channel, selectionKey, onDisconnect);
    }

}
