package jpize.util.net.tcp;

import jpize.util.net.tcp.packet.IPacket;
import jpize.util.Utils;
import jpize.util.io.ExtDataOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class TcpServer implements Closeable {

    private ServerSocket serverSocket;
    private CopyOnWriteArrayList<TcpConnection> connectionList;
    private final TcpListener listener;

    public TcpServer(TcpListener listener) {
        this.listener = listener;
    }


    public void run(int port) {
        if(!isClosed())
            throw new RuntimeException("TCP Server already running");

        try{
            serverSocket = new ServerSocket(port);
            connectionList = new CopyOnWriteArrayList<>();
            waitConnections();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public TcpServer run(String host, int port) {
        if(!isClosed())
            throw new RuntimeException("TCP Server already running");

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
        final Thread connectorThread = new Thread(() -> {
            try{
                while(!Thread.interrupted()){
                    final TcpConnection connection = new TcpConnection(serverSocket.accept(), listener::received, (thatConnection) -> {
                        listener.disconnected(thatConnection);
                        connectionList.remove(thatConnection);
                    });
                    connectionList.add(connection);
                    listener.connected(connection);
                }
            }catch(IOException ignored){ }
        }, "TCP-server Thread #" + this.hashCode());

        connectorThread.setPriority(Thread.MIN_PRIORITY);
        connectorThread.setDaemon(true);
        connectorThread.start();
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


    public void broadcast(IPacket<?> packet) {
        try{
            final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            final ExtDataOutputStream dataStream = new ExtDataOutputStream(byteStream);

            dataStream.writeInt(packet.getPacketID());
            packet.write(dataStream);

            this.broadcast(byteStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void broadcast(TcpConnection except, IPacket<?> packet) {
        try{
            final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            final ExtDataOutputStream dataStream = new ExtDataOutputStream(byteStream);

            dataStream.writeInt(packet.getPacketID());
            packet.write(dataStream);

            this.broadcast(except, byteStream);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    public Collection<TcpConnection> getConnections() {
        return connectionList;
    }


    public boolean isClosed() {
        return (serverSocket == null || serverSocket.isClosed());
    }

    @Override
    public void close() {
        if(isClosed())
            return;

        for(TcpConnection connection: connectionList)
            connection.close();
        connectionList.clear();

        Utils.close(serverSocket);
    }

}
