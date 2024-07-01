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
        if(isConnected())
            throw new RuntimeException("TCP-Client already connected.");

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


    public TcpConnection getConnection() {
        return connection;
    }

    public boolean isConnected() {
        return (connection != null && connection.isConnected());
    }

    public boolean isClosed(){
        return (connection == null || connection.isClosed());
    }

    public void disconnect() {
        if(isConnected())
            connection.close();
    }


    public void encode(KeyAes encodeKey) {
        if(isConnected())
            connection.encode(encodeKey);
    }

    public void send(byte[] packet) {
        if(isConnected())
            connection.send(packet);
    }

    public void send(ByteArrayOutputStream stream) {
        if(isConnected())
            connection.send(stream);
    }

    public void send(IPacket<?> packet) {
        if(isConnected())
            connection.send(packet);
    }

}
