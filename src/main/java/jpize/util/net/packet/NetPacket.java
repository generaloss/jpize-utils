package jpize.util.net.packet;

import jpize.util.io.ExtDataInputStream;
import jpize.util.io.ExtDataOutputStream;

import java.io.IOException;

public abstract class NetPacket<H> {

    private final short ID;

    public NetPacket() {
        this.ID = getIDByClass(this.getClass());
    }

    public short getPacketID() {
        return ID;
    }

    abstract public void write(ExtDataOutputStream stream) throws IOException;

    abstract public void read(ExtDataInputStream stream) throws IOException;

    abstract public void handle(H handler);


    public static short getIDByClass(Class<?> c) {
        final int nameHash = c.getSimpleName().hashCode();
        return (short) (nameHash ^ (nameHash << 16));
    }

}
