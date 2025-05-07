package jpize.util.net.tcp;

@FunctionalInterface
public interface TCPCloseable {

    void close(TCPConnection connection, String message);

}
