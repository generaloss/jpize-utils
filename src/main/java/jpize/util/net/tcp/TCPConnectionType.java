package jpize.util.net.tcp;

public enum TCPConnectionType {

    DEFAULT (DefaultTCPConnection.class),
    PACKET  (PacketTCPConnection.class);

    private final Class<?> conectionClass;

    TCPConnectionType(Class<?> conectionClass) {
        this.conectionClass = conectionClass;
    }

    public Class<?> getConnectionClass() {
        return conectionClass;
    }

}
