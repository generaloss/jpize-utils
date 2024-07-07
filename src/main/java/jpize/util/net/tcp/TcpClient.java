package jpize.util.net.tcp;

import jpize.util.function.IOConsumer;
import jpize.util.io.ExtDataOutputStream;
import jpize.util.security.KeyAes;
import jpize.util.net.tcp.packet.IPacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.function.Consumer;

public class TcpClient {

    private Consumer<TcpConnection> onConnect, onDisconnect;
    private TcpListener onReceive;
    private TcpConnection connection;

    public void setOnConnect(Consumer<TcpConnection> onConnect) {
        this.onConnect = onConnect;
    }

    public void setOnDisconnect(Consumer<TcpConnection> onDisconnect) {
        this.onDisconnect = onDisconnect;
    }

    public void setOnReceive(TcpListener onReceive) {
        this.onReceive = onReceive;
    }


    public TcpClient connect(String host, int port) {
        if(isConnected())
            throw new RuntimeException("TCP-Client already connected.");

        try{
            final Socket socket = new Socket();
            final InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(host), port);
            socket.connect(socketAddress);

            connection = new TcpConnection(socket, onReceive, onDisconnect);
            if(onConnect != null)
                onConnect.accept(connection);
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

    public void send(IOConsumer<ExtDataOutputStream> streamConsumer) {
        if(isConnected())
            connection.send(streamConsumer);
    }

    public void send(IPacket<?> packet) {
        if(isConnected())
            connection.send(packet);
    }

}
