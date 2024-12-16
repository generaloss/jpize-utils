package jpize.util.net.tcp;

@FunctionalInterface
public interface TCPListener {
    
    void receive(TCPConnection sender, byte[] bytes);

}
