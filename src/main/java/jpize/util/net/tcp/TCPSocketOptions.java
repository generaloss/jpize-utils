package jpize.util.net.tcp;

import java.net.Socket;
import java.net.SocketException;

public class TCPSocketOptions {

    private final Socket socket;

    public TCPSocketOptions(Socket socket) {
        if(socket == null)
            throw new NullPointerException("socket is null");
        this.socket = socket;
    }

    public Socket socket() {
        return socket;
    }


    public boolean getTcpNoDelay() {
        try{
            return socket.getTcpNoDelay();
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }

    public TCPSocketOptions setTcpNoDelay(boolean on) {
        try{
            socket.setTcpNoDelay(on);
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return this;
    }


    public int getSoLinger() {
        try{
            return socket.getSoLinger();
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }

    public TCPSocketOptions setSoLinger(boolean on, int linger) {
        try{
            socket.setSoLinger(on, linger);
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return this;
    }


    public boolean getKeepAlive() {
        try{
            return socket.getKeepAlive();
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }

    public TCPSocketOptions setKeepAlive(boolean on) {
        try{
            socket.setKeepAlive(on);
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return this;
    }


    public int getSendBufferSize() {
        try{
            return socket.getSendBufferSize();
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }

    public TCPSocketOptions setSendBufferSize(int size) {
        try{
            socket.setSendBufferSize(size);
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return this;
    }


    public int getReceiveBufferSize() {
        try{
            return socket.getReceiveBufferSize();
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }

    public TCPSocketOptions setReceiveBufferSize(int size) {
        try{
            socket.setReceiveBufferSize(size);
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return this;
    }


    public int getTrafficClass() {
        try{
            return socket.getTrafficClass();
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }

    public TCPSocketOptions setTrafficClass(int typeOfService) {
        try{
            socket.setTrafficClass(typeOfService);
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return this;
    }


    public boolean getReuseAddress() {
        try{
            return socket.getReuseAddress();
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }

    public TCPSocketOptions setReuseAddress(boolean on) {
        try{
            socket.setReuseAddress(on);
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return this;
    }


    public boolean getOOBInline() {
        try{
            return socket.getOOBInline();
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
    }

    public TCPSocketOptions setOOBInline(boolean on) {
        try{
            socket.setOOBInline(on);
        }catch(SocketException e){
            throw new RuntimeException(e);
        }
        return this;
    }


    @Override
    public String toString() {
        return "TCPSocketOptions{" +
            "tcpNoDelay=" + this.getTcpNoDelay() +
            ", soLinger=" + this.getSoLinger() +
            ", keepAlive=" + this.getKeepAlive() +
            ", sendBufferSize=" + this.getSendBufferSize() +
            ", receiveBufferSize=" + this.getReceiveBufferSize() +
            ", trafficClass=" + this.getTrafficClass() +
            ", reuseAddress=" + this.getReuseAddress() +
            ", oobInline=" + this.getOOBInline() +
            '}';
    }

}
