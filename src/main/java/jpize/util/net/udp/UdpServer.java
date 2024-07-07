package jpize.util.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class UdpServer {

    private final DatagramSocket socket;
    private final Consumer<DatagramPacket> packetConsumer;
    private final Thread thread;

    public UdpServer(int port, Consumer<DatagramPacket> packetConsumer){
        try{
            this.socket = new DatagramSocket(port);
            this.packetConsumer = packetConsumer;
            this.thread = startReceiveLoop();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public UdpServer(String host, int port, Consumer<DatagramPacket> packetConsumer){
        try{
            this.socket = new DatagramSocket(port, InetAddress.getByName(host));
            this.packetConsumer = packetConsumer;
            this.thread = startReceiveLoop();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    private Thread startReceiveLoop(){
        final Thread thread = new Thread(this::receiveLoop);
        thread.setDaemon(true);
        thread.start();
        return thread;
    }

    private void receiveLoop(){
        try{
            while(!isClosed()){ //! !Thread.interrupted()
                // receive size
                final DatagramPacket sizePacket = new DatagramPacket(new byte[4], 4);
                socket.receive(sizePacket);
                final int size = ByteBuffer.wrap(sizePacket.getData()).getInt();
                if(size < 1)
                    continue;

                // receive data
                final DatagramPacket packet = new DatagramPacket(new byte[size], size);
                socket.receive(packet);

                // accept packet
                packetConsumer.accept(packet);
            }
        }catch(IOException ignored){ }
    }


    public DatagramSocket getSocket() {
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
        thread.interrupt();
        socket.close();
    }

}
