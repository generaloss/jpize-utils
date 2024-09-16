package jpize.util.net.tcp;

@FunctionalInterface
public interface TcpListener {
    
    void receive(TcpConnection sender, byte[] bytes);

}
