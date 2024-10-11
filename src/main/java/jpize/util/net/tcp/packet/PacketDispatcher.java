package jpize.util.net.tcp.packet;

import jpize.util.io.ExtDataInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PacketDispatcher {

    private final Map<Short, Class<? extends IPacket<?>>> packetClasses;
    private final Queue<Runnable> toHandleQueue;

    public PacketDispatcher() {
        this.packetClasses = new ConcurrentHashMap<>();
        this.toHandleQueue = new ConcurrentLinkedQueue<>();
    }

    @SafeVarargs
    public final PacketDispatcher register(Class<? extends IPacket<?>>... packetClasses) {
        for(Class<? extends IPacket<?>> packetClass: packetClasses){
            final short ID = IPacket.makeID(packetClass);
            this.packetClasses.put(ID, packetClass);
        }
        return this;
    }

    public int handlePackets() {
        int count = 0;
        while(!toHandleQueue.isEmpty()){
            final Runnable handleRunnable = toHandleQueue.poll();
            count++;
            handleRunnable.run();
        }
        return count;
    }

    public boolean readPacket(byte[] bytes, PacketHandler handler) {
        try{
            // check
            if(bytes.length < 2) // 'short' (ID) size = 2
                return false;

            // create stream from bytes
            final ExtDataInputStream dataStream = new ExtDataInputStream(new ByteArrayInputStream(bytes));

            // read ID and get packet class
            final short ID = dataStream.readShort();
            final Class<? extends IPacket<?>> packetClass = packetClasses.get(ID);
            if(packetClass == null)
                return false;

            // create packet class instance and read remaining data
            final IPacket<PacketHandler> packetInstance = instancePacket(packetClass);
            packetInstance.read(dataStream);

            // handle and return
            toHandleQueue.add(() -> packetInstance.handle(handler));
            return true;

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private IPacket<PacketHandler> instancePacket(Class<?> packetClass) {
        try{
            final Constructor<?> constructor = packetClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return (IPacket<PacketHandler>) constructor.newInstance();

        }catch(Exception e){
            throw new RuntimeException("Failed to instance packet " + packetClass.getName(), e);
        }
    }

}