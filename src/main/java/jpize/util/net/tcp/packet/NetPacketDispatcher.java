package jpize.util.net.tcp.packet;

import jpize.util.io.ExtDataInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetPacketDispatcher {

    private final Map<Short, Class<? extends NetPacket<?>>> packetClasses;
    private final Queue<Runnable> toHandleQueue;

    public NetPacketDispatcher() {
        this.packetClasses = new ConcurrentHashMap<>();
        this.toHandleQueue = new ConcurrentLinkedQueue<>();
    }

    @SafeVarargs
    public final NetPacketDispatcher register(Class<? extends NetPacket<?>>... packetClasses) {
        for(Class<? extends NetPacket<?>> packetClass : packetClasses){
            final short ID = NetPacket.getIDByClass(packetClass);
            this.packetClasses.put(ID, packetClass);
        }
        return this;
    }

    public boolean readPacket(byte[] bytes, INetPacketHandler handler) {
        try{
            // check
            if(bytes.length < 2) // 'short' (ID) size = 2
                return false;

            // create stream from bytes
            final ExtDataInputStream dataStream = new ExtDataInputStream(new ByteArrayInputStream(bytes));

            // read ID and get packet class
            final short ID = dataStream.readShort();
            final Class<? extends NetPacket<?>> packetClass = packetClasses.get(ID);
            if(packetClass == null)
                return false;

            // create packet class instance and read remaining data
            final NetPacket<INetPacketHandler> packetInstance = instancePacket(packetClass);
            packetInstance.read(dataStream);

            // handle and return
            toHandleQueue.add(() -> packetInstance.handle(handler));
            return true;

        }catch(IOException e){
            throw new RuntimeException("Unable to read packet: " + e);
        }
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

    private NetPacket<INetPacketHandler> instancePacket(Class<?> packetClass) {
        try{
            final Constructor<?> constructor = packetClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            // noinspection unchecked
            return (NetPacket<INetPacketHandler>) constructor.newInstance();

        }catch(Exception e){
            throw new RuntimeException("Unable to instance packet: " + packetClass.getName(), e);
        }
    }

}