package jpize.util.net.tcp;

import jpize.util.security.KeyAes;
import jpize.util.net.tcp.packet.IPacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TcpClient {

    private TcpConnection connection;
    private final TcpListener listener;

    public TcpClient(TcpListener listener) {
        this.listener = listener;
    }


    public TcpClient connect(String host, int port) {
        if(connection != null && !connection.isClosed())
            return this;

        try{
            final Socket socket = new Socket();
            final InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(host), port);
            socket.connect(socketAddress);

            connection = new TcpConnection(socket, listener::received, listener::disconnected);
            listener.connected(connection);
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        return this;
    }


    public void send(byte[] packet) {
        if(connection != null)
            connection.send(packet);
    }

    public void send(ByteArrayOutputStream stream) {
        if(connection != null)
            connection.send(stream);
    }

    public void send(IPacket<?> packet) {
        if(connection != null)
            connection.send(packet);
    }


    public void encode(KeyAes encodeKey) {
        connection.encode(encodeKey);
    }


    synchronized public void disconnect() {
        if(connection == null || connection.isClosed())
            return;

        connection.close();
    }


    public TcpConnection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return connection != null && connection.isConnected();
    }

}
