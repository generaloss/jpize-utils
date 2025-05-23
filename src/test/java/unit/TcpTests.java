package unit;

import jpize.util.io.ExtDataInputStream;
import jpize.util.io.ExtDataOutputStream;
import jpize.util.net.tcp.TCPClient;
import jpize.util.net.tcp.TCPConnection;
import jpize.util.net.tcp.TCPServer;
import jpize.util.net.packet.NetPacket;
import jpize.util.net.packet.NetPacketDispatcher;
import jpize.util.security.AESKey;
import jpize.util.time.TimeUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpTests {

    public static void main(String[] args) {
        final TCPServer server = new TCPServer()
            .setOnConnect((connection) -> System.out.println("SC: Connected"))
            .setOnDisconnect((connection, message) -> System.out.println("SC: Disconnected: " + message))
            .run(65000);

        final TCPClient client = new TCPClient()
            .setOnConnect((connection) -> System.out.println("CC: Connected"))
            .setOnDisconnect((connection, message) -> System.out.println("CC: Disconnected: " + message));
        for(int i = 0; i < 3; i++){
            client.connect("localhost", 65000);
            client.disconnect();
        }
        TimeUtils.delayMillis(2000);
        server.close();
    }

    private static void reliabilityTest() {
        // iterate all tests 1000 times
        final TcpTests tests = new TcpTests();
        for(int i = 0; i < 1000; i++){
            System.out.println(i + 1);
            for(Method method: TcpTests.class.getMethods()){
                if(method.isAnnotationPresent(Test.class)){
                    method.setAccessible(true);
                    try{
                        method.invoke(tests);
                    }catch(Exception e){
                        throw new RuntimeException("Exception in test '" + method.getName() + "': ", e);
                    }
                }
            }
        }
    }

    @Test
    public void reconnect_client() {
        final int reconnectsNum = 100;
        final AtomicInteger counter = new AtomicInteger();
        final TCPServer server = new TCPServer()
                .setOnConnect((connection) -> counter.incrementAndGet())
                .setOnDisconnect((connection, message) -> counter.incrementAndGet())
                .run(65000);

        final TCPClient client = new TCPClient();
        for(int i = 0; i < reconnectsNum; i++){
            client.connect("localhost", 65000);
            client.disconnect();
        }
        TimeUtils.waitFor(() -> counter.get() == reconnectsNum * 2, 500, () -> Assert.fail(counter.get() + " / " + (reconnectsNum * 2)));
        server.close();
    }

    @Test
    public void disconnect_client() {
        final AtomicBoolean closed = new AtomicBoolean();
        final TCPServer server = new TCPServer()
                .setOnConnect(TCPConnection::close)
                .run(5406);
        new TCPClient()
                .setOnDisconnect((connection, message) -> closed.set(true))
                .connect("localhost", 5406);
        TimeUtils.waitFor(closed::get, 500, Assert::fail);
        server.close();
    }

    @Test
    public void close_server_connection() {
        final AtomicBoolean closed = new AtomicBoolean();
        final TCPServer server = new TCPServer()
                .setOnDisconnect((connection, message) -> closed.set(true))
                .run(5407);
        final TCPClient client = new TCPClient();
        client.connect("localhost", 5407);
        client.disconnect();
        TimeUtils.waitFor(closed::get, 500, Assert::fail);
        server.close();
    }

    @Test
    public void send_hello_world_to_server() {
        final String message = "Hello, World!";

        final TCPServer server = new TCPServer()
                .setOnReceive((sender, bytes) -> {
                    final String received = new String(bytes);
                    Assert.assertEquals(message, received);
                    sender.close();
                })
                .run(5400);

        final TCPClient client = new TCPClient();
        client.connect("localhost", 5400);
        client.send(message.getBytes());

        TimeUtils.waitFor(client::isClosed, 1000, Assert::fail);
        server.close();
    }

    @Test
    public void send_hello_world_encrypted() {
        final AESKey key = new AESKey(128);
        final String message = "Hello, World!";

        final TCPServer server = new TCPServer()
                .setOnConnect((connection) -> connection.encode(key))
                .setOnReceive((sender, bytes) -> {
                    final String received = new String(bytes);
                    Assert.assertEquals(message, received);
                    sender.close();
                })
                .run(5405);

        final TCPClient client = new TCPClient();
        client.connect("localhost", 5405);
        client.encode(key);
        client.send(message.getBytes());

        TimeUtils.waitFor(client::isClosed);
        server.close();
    }

    @Test
    public void send_a_lot_of_data_encrypted() {
        final AESKey key = new AESKey(256);
        final String message = "0123456789".repeat(1000);

        final AtomicInteger counter = new AtomicInteger();
        final TCPServer server = new TCPServer()
                .setOnConnect((connection) -> connection.encode(key))
                .setOnReceive((sender, bytes) -> {
                    final String received = new String(bytes);
                    counter.incrementAndGet();
                    if(!message.equals(received)){
                        Assert.fail();
                        sender.close();
                    }
                })
                .run(5408);

        final TCPClient client = new TCPClient();
        client.connect("localhost", 5408);
        client.encode(key);

        final int iterations = 10000;
        for(int i = 0; i < iterations; i++)
            client.send(message.getBytes());

        TimeUtils.waitFor(() -> counter.get() == iterations, 5000, Assert::fail);
        server.close();
    }

    @Test
    public void send_hello_world_to_client() {
        final String message = "Hello, World!";

        final TCPServer server = new TCPServer()
                .setOnConnect((connection) -> connection.send(message.getBytes()))
                .run(5401);

        final TCPClient client = new TCPClient();
        client.setOnReceive((connection, bytes) -> {
            final String received = new String(bytes);
            Assert.assertEquals(message, received);
            connection.close();
        });
        client.connect("localhost", 5401);

        TimeUtils.waitFor(client::isClosed);
        server.close();
    }

    @Test
    public void send_a_lot_of_data_to_server() {
        final String message = "Hello, Data! ".repeat(100000);

        final TCPServer server = new TCPServer()
                .setOnReceive((sender, bytes) -> {
                    final String received = new String(bytes);
                    Assert.assertEquals(received, message);
                    sender.close();
                })
                .run(5402);

        final TCPClient client = new TCPClient();
        client.connect("localhost", 5402);
        client.send(message.getBytes());

        TimeUtils.waitFor(client::isClosed, 2000, Assert::fail);
        server.close();
    }

    @Test
    public void connect_a_lot_of_clients_and_send_a_lot_of_data_multithreaded() {
        final String message = "Hello, World! ".repeat(10000);
        final int clientsAmount = 100;
        final AtomicInteger done = new AtomicInteger();

        final TCPServer server = new TCPServer()
                .setOnReceive((sender, bytes) -> {
                    final String received = new String(bytes);
                    Assert.assertEquals(received, message);
                    done.incrementAndGet();
                })
                .run(5404);

        final List<TCPClient> clients = new CopyOnWriteArrayList<>();
        for(int i = 0; i < clientsAmount; i++){
            final TCPClient client = new TCPClient();
            client.connect("localhost", 5404);
            clients.add(client);
        }

        for(TCPClient client: clients)
            new Thread(() -> {
                client.send(message.getBytes());
                client.disconnect();
            }).start();

        int prevDone = -1;
        while(done.get() != clientsAmount){
            if(done.get() != prevDone)
                prevDone = done.get();
            Thread.onSpinWait();
        }
        server.close();
    }


    @Test
    public void send_packet() {
        final String message = "Hello, World!";

        final NetPacketDispatcher dispatcher = new NetPacketDispatcher()
                .register(MsgPacket.class);

        final AtomicInteger counter = new AtomicInteger();
        final MsgHandler handler = (received) -> {
            Assert.assertEquals(message, received);
            counter.incrementAndGet();
        };

        final TCPServer server = new TCPServer()
                .setOnReceive((sender, bytes) -> {
                    dispatcher.readPacket(bytes, handler);
                    dispatcher.handlePackets();
                })
                .run(5403);

        final TCPClient client = new TCPClient();
        client.connect("localhost", 5403);
        client.send(new MsgPacket(message));
        client.send(new MsgPacket(message));

        TimeUtils.waitFor(() -> (counter.get() == 2), 2000, Assert::fail);
        server.close();
    }

    interface MsgHandler {
        void acceptMsg(String message);
    }

    static class MsgPacket extends NetPacket<MsgHandler> {
        private String message;
        public MsgPacket(String message) {
            this.message = message;
        }
        public MsgPacket() { }
        public void write(ExtDataOutputStream stream) throws IOException {
            stream.writeByteString(message);
        }
        public void read(ExtDataInputStream stream) throws IOException {
            message = stream.readByteString();
        }
        public void handle(MsgHandler handler) {
            handler.acceptMsg(message);
        }
    }

}
