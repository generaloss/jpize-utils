package jpize.util.net.tcp;

import jpize.util.function.IOConsumer;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.Utils;
import jpize.util.io.ExtDataOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class TcpServer {

    private Consumer<TcpConnection> onConnect, onDisconnect;
    private TcpListener onReceive;
    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<TcpConnection> connectionList;

    public void setOnConnect(Consumer<TcpConnection> onConnect) {
        this.onConnect = onConnect;
    }

    public void setOnDisconnect(Consumer<TcpConnection> onDisconnect) {
        this.onDisconnect = onDisconnect;
    }

    public void setOnReceive(TcpListener onReceive) {
        this.onReceive = onReceive;
    }


    public void run(int port) {
        if(isAlive())
            throw new RuntimeException("TCP-Server already running.");

        try{
            serverSocket = new ServerSocket(port);
            connectionList = new CopyOnWriteArrayList<>();
            waitConnections();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public TcpServer run(String host, int port) {
        if(isAlive())
            throw new RuntimeException("TCP-Server already running.");

        try{
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName(host), port));
            connectionList = new CopyOnWriteArrayList<>();
            waitConnections();
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        return this;
    }

    private void waitConnections() {
        final Consumer<TcpConnection> disconnectConsumer = (connection) -> {
            if(onDisconnect != null)
                onDisconnect.accept(connection);
            connectionList.remove(connection);
        };

        final Thread connectorThread = new Thread(() -> {
            try{
                while(!Thread.interrupted()){
                    final TcpConnection connection = new TcpConnection(serverSocket.accept(), onReceive, disconnectConsumer);
                    connectionList.add(connection);
                    if(onConnect != null)
                        onConnect.accept(connection);
                }
            }catch(IOException ignored){ }
        }, "TCP-server Thread #" + this.hashCode());

        connectorThread.setPriority(Thread.MIN_PRIORITY);
        connectorThread.setDaemon(true);
        connectorThread.start();
    }


    public Collection<TcpConnection> getConnections() {
        return connectionList;
    }

    public boolean isAlive() {
        return (serverSocket != null && !serverSocket.isClosed());
    }

    public boolean isClosed() {
        return (serverSocket == null || serverSocket.isClosed());
    }

    public void close() {
        if(isClosed())
            return;

        for(TcpConnection connection: connectionList)
            connection.close();
        connectionList.clear();

        Utils.close(serverSocket);
    }


    public void broadcast(byte[] bytes) {
        for(TcpConnection connection: connectionList)
            connection.send(bytes);
    }

    public void broadcast(TcpConnection except, byte[] bytes) {
        for(TcpConnection connection: connectionList)
            if(connection != except)
                connection.send(bytes);
    }

    public void broadcast(ByteArrayOutputStream stream) {
        this.broadcast(stream.toByteArray());
    }

    public void broadcast(TcpConnection except, ByteArrayOutputStream stream) {
        this.broadcast(except, stream.toByteArray());
    }

    public void broadcast(IOConsumer<ExtDataOutputStream> streamConsumer) {
        try{
            final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            final ExtDataOutputStream dataStream = new ExtDataOutputStream(byteStream);
            streamConsumer.accept(dataStream);
            this.broadcast(byteStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void broadcast(TcpConnection except, IOConsumer<ExtDataOutputStream> streamConsumer) {
        try{
            final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            final ExtDataOutputStream dataStream = new ExtDataOutputStream(byteStream);
            streamConsumer.accept(dataStream);
            this.broadcast(except, byteStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void broadcast(IPacket<?> packet) {
        this.broadcast(dataStream -> {
            dataStream.writeShort(packet.getPacketID());
            packet.write(dataStream);
        });
    }

    public void broadcast(TcpConnection except, IPacket<?> packet) {
        this.broadcast(except, dataStream -> {
            dataStream.writeShort(packet.getPacketID());
            packet.write(dataStream);
        });
    }

}
