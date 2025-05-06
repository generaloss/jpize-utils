package jpize.util.net.tcp;

public interface TCPCloseable {

    String CONNECTION_CLOSED = "Connection closed";
    String SERVER_CLOSED = "Server closed";
    String CLIENT_CLOSED = "Client closed";
    String CLOSED_BY_OTHER_SIDE = "Connection closed by other side";

    void close(TCPConnection connection, String message);

}
