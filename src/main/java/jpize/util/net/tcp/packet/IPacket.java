package jpize.util.net.tcp.packet;

import jpize.util.io.ExtDataInputStream;
import jpize.util.io.ExtDataOutputStream;

import java.io.IOException;

public abstract class IPacket<T extends PacketHandler> {

    private final short ID;

    public IPacket() {
        this.ID = makeID(this.getClass());
    }

    public short getPacketID() {
        return ID;
    }

    abstract public void write(ExtDataOutputStream stream) throws IOException;

    abstract public void read(ExtDataInputStream stream) throws IOException;

    abstract public void handle(T handler);


    public static short makeID(Class<?> c) {
        final int nameHash = c.getSimpleName().hashCode();
        return (short) (nameHash ^ (nameHash << 16));
    }

}
