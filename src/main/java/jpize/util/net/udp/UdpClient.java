package jpize.util.net.udp;

import java.io.IOException;
import java.net.*;

public class UdpClient {

    private final DatagramSocket socket;

    public UdpClient(String host, int port) {
        try{
            this.socket = new DatagramSocket();
            this.socket.connect(InetAddress.getByName(host), port);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    public void send(byte[] bytes, SocketAddress address) {
        if(isClosed())
            return;

        try{
            // make size
            final byte[] size = new byte[]{
                (byte) (bytes.length >>> 24),
                (byte) (bytes.length >>> 16),
                (byte) (bytes.length >>> 8 ),
                (byte) (bytes.length       )
            };

            // send size
            final DatagramPacket sizePacket = new DatagramPacket(size, size.length, address);
            socket.send(sizePacket);

            // send data
            final DatagramPacket dataPacket = new DatagramPacket(bytes, bytes.length, address);
            socket.send(dataPacket);

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void send(byte[] bytes, String host, int port) {
        this.send(bytes, new InetSocketAddress(host, port));
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
        if(isConnected())
            socket.close();
    }

}
