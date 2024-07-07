package jpize.util.net.tcp;

import jpize.util.function.IOConsumer;
import jpize.util.security.KeyAes;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.Utils;
import jpize.util.io.ExtDataOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.function.Consumer;

public class TcpConnection {

    private final Socket socket;
    private final TcpListener onReceive;
    private final Consumer<TcpConnection> onDisconnect;
    private final DataOutputStream outStream;
    private final Thread receiveThread;
    private KeyAes encodeKey;


    protected TcpConnection(Socket socket, TcpListener onReceive, Consumer<TcpConnection> onDisconnect) throws IOException {
        this.socket = socket;
        this.onReceive = onReceive;
        this.onDisconnect = onDisconnect;

        this.outStream = new DataOutputStream(socket.getOutputStream());

        this.receiveThread = new Thread(this::receiveLoop, "TCP-connection Thread #" + this.hashCode());
        this.receiveThread.setDaemon(true);
        this.receiveThread.start();
    }

    private void receiveLoop() {
        try{
            final DataInputStream inStream = new DataInputStream(socket.getInputStream());

            while(!isClosed()){ //! !Thread.interrupted()
                Utils.waitFor(() -> onReceive != null);

                final int length = inStream.readInt();
                if(length < 0)
                    continue;

                final byte[] bytes = tryToDecryptBytes(inStream.readNBytes(length));
                if(bytes == null)
                    continue;

                onReceive.receive(this, bytes);
            }
        }catch(IOException ignored){
            close();
        }
    }


    private void checkState() {
        if(isClosed())
            throw new IllegalStateException("TCP-connection is closed.");
    }

    public void send(byte[] bytes) {
        try{
            this.checkState();
            bytes = tryToEncryptBytes(bytes);
            outStream.writeInt(bytes.length);
            outStream.write(bytes);
        }catch(IOException e){
            throw new RuntimeException(e);
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


    public void encode(KeyAes encodeKey) {
        this.encodeKey = encodeKey;
    }

    private byte[] tryToEncryptBytes(byte[] bytes) {
        if(encodeKey == null){
            return bytes;
        }else
            return encodeKey.encrypt(bytes);
    }

    private byte[] tryToDecryptBytes(byte[] bytes) {
        if(encodeKey == null){
            return bytes;
        }else
            return encodeKey.decrypt(bytes);
    }


    public Socket getSocket() {
        return socket;
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public void close() {
        if(isClosed())
            return;
        if(onDisconnect != null)
            onDisconnect.accept(this);
        receiveThread.interrupt();
        Utils.close(socket);
    }


    public int getPort() {
        return socket.getPort();
    }

    public int getLocalPort() {
        return socket.getLocalPort();
    }

    public InetAddress getAddress() {
        return socket.getInetAddress();
    }

    public InetAddress getLocalAddress() {
        return socket.getLocalAddress();
    }


    public boolean getTcpNoDelay() {
        try{ return socket.getTcpNoDelay(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSoTimeout() {
        try{ return socket.getSoTimeout(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getKeepAlive() {
        try{ return socket.getKeepAlive(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSendBufferSize() {
        try{ return socket.getSendBufferSize(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getReceiveBufferSize() {
        try{ return socket.getReceiveBufferSize(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getTrafficClass() {
        try{ return socket.getTrafficClass(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getReuseAddress() {
        try{ return socket.getReuseAddress(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public boolean getOOBInline() {
        try{ return socket.getOOBInline(); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public int getSoLinger() {
        try{ return socket.getSoLinger(); }catch(SocketException e){ throw new RuntimeException(e); }
    }


    public void setTcpNoDelay(boolean on) {
        try{ socket.setTcpNoDelay(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSoTimeout(int timeout) {
        try{ socket.setSoTimeout(timeout); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setKeepAlive(boolean on) {
        try{ socket.setKeepAlive(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSendBufferSize(int size) {
        try{ socket.setSendBufferSize(size); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setReceiveBufferSize(int size) {
        try{ socket.setReceiveBufferSize(size); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setTrafficClass(int trafficClass) {
        try{ socket.setTrafficClass(trafficClass); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setReuseAddress(boolean on) {
        try{ socket.setReuseAddress(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setOOBInline(boolean on) {
        try{ socket.setOOBInline(on); }catch(SocketException e){ throw new RuntimeException(e); }
    }

    public void setSoLinger(boolean on, int linger) {
        try{ socket.setSoLinger(on, linger); }catch(SocketException e){ throw new RuntimeException(e); }
    }

}
