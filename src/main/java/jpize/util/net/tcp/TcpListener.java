package jpize.util.net.tcp;

public interface TcpListener {
    
    void received(TcpConnection sender, byte[] bytes);

    void connected(TcpConnection connection);

    void disconnected(TcpConnection connection);

}
