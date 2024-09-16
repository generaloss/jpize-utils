package jpize.util.net.tcp;

import jpize.util.function.IOConsumer;
import jpize.util.security.KeyAes;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.Utils;
import jpize.util.io.ExtDataOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

public class TcpConnection {

    private final SocketChannel channel;
    private final SelectionKey selectionKey;
    private final Consumer<TcpConnection> onDisconnect;
    private KeyAes encodeKey;
    private final ByteBuffer lengthBuf;
    private ByteBuffer dataBuf;
    private int dataRemaining;

    protected TcpConnection(SocketChannel channel, SelectionKey selectionKey, Consumer<TcpConnection> onDisconnect) {
        this.channel = channel;
        this.selectionKey = selectionKey;
        this.onDisconnect = onDisconnect;
        this.lengthBuf = ByteBuffer.allocate(4); // 4 bytes for integer
    }

    public SelectionKey getSelectionKey() {
        return selectionKey;
    }


    protected void readBytes(Consumer<byte[]> bytesConsumer) {
        try{
            // if previous buffer is full allocate new buffer
            if(isDataBufferFull() && !allocateDataBuffer())
                return;
            // read bytes and when the buffer is full accept them
            dataRemaining -= channel.read(dataBuf);
            if(isDataBufferFull()){
                final byte[] bytes = tryToDecryptBytes(dataBuf.array());
                dataBuf.clear();
                bytesConsumer.accept(bytes);
            }
        }catch(IOException ignore){
            close();
        }
    }

    private boolean isDataBufferFull() {
        return dataRemaining == 0;
    }

    private boolean allocateDataBuffer() throws IOException {
        // try to read length
        final int read = channel.read(lengthBuf);

        if(read == -1) { // connection was closed on the other side
            close();
            return false;
        }else if(read < 4) // not int size (length)
            return false;

        // read length
        lengthBuf.flip();
        dataRemaining = lengthBuf.getInt();
        lengthBuf.clear();

        // allocate buffer
        dataBuf = ByteBuffer.allocate(dataRemaining);
        return true;
    }


    private byte[] tryToDecryptBytes(byte[] bytes) {
        if(encodeKey == null){
            return bytes;
        }else
            return encodeKey.decrypt(bytes);
    }

    private byte[] tryToEncryptBytes(byte[] bytes) {
        if(encodeKey == null){
            return bytes;
        }else
            return encodeKey.encrypt(bytes);
    }

    public void encode(KeyAes encodeKey) {
        this.encodeKey = encodeKey;
    }


    private void checkState() {
        if(isClosed())
            throw new IllegalStateException("TCP-connection is closed.");
    }


    public void send(byte[] bytes) {
        try{
            this.checkState();
            bytes = tryToEncryptBytes(bytes);

            final ByteBuffer buffer = ByteBuffer.allocate(4 + bytes.length);
            buffer.putInt(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        }catch(IOException e){
            close();
        }
    }

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


    public SocketChannel getChannel() {
        return channel;
    }

    public Socket getSocket() {
        return channel.socket();
    }

    public boolean isConnected() {
        return getSocket().isConnected();
    }

    public boolean isClosed() {
        return getSocket().isClosed();
    }

    public void close() {
        if(isClosed())
            return;
        if(onDisconnect != null)
            onDisconnect.accept(this);
        Utils.close(channel);
    }


    public int getPort() {
        return getSocket().getPort();
    }

    public int getLocalPort() {
        return getSocket().getLocalPort();
    }

    public InetAddress getAddress() {
        return getSocket().getInetAddress();
    }

    public InetAddress getLocalAddress() {
        return getSocket().getLocalAddress();
    }


    public boolean getTcpNoDelay() {
        try{ return getSocket().getTcpNoDelay(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSoTimeout() {
        try{ return getSocket().getSoTimeout(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getKeepAlive() {
        try{ return getSocket().getKeepAlive(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSendBufferSize() {
        try{ return getSocket().getSendBufferSize(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getReceiveBufferSize() {
        try{ return getSocket().getReceiveBufferSize(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getTrafficClass() {
        try{ return getSocket().getTrafficClass(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getReuseAddress() {
        try{ return getSocket().getReuseAddress(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getOOBInline() {
        try{ return getSocket().getOOBInline(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSoLinger() {
        try{ return getSocket().getSoLinger(); }catch(SocketException e){ throw new RuntimeException(e); }
    }


    public void setTcpNoDelay(boolean on) {
        try{ getSocket().setTcpNoDelay(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSoTimeout(int timeout) {
        try{ getSocket().setSoTimeout(timeout); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setKeepAlive(boolean on) {
        try{ getSocket().setKeepAlive(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSendBufferSize(int size) {
        try{ getSocket().setSendBufferSize(size); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setReceiveBufferSize(int size) {
        try{ getSocket().setReceiveBufferSize(size); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setTrafficClass(int trafficClass) {
        try{ getSocket().setTrafficClass(trafficClass); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setReuseAddress(boolean on) {
        try{ getSocket().setReuseAddress(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setOOBInline(boolean on) {
        try{ getSocket().setOOBInline(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSoLinger(boolean on, int linger) {
        try{ getSocket().setSoLinger(on, linger); }catch(SocketException e){ throw new RuntimeException(e); }
    }

}
