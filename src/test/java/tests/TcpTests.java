package tests;

import jpize.util.io.ExtDataInputStream;
import jpize.util.io.ExtDataOutputStream;
import jpize.util.net.tcp.TCPClient;
import jpize.util.net.tcp.TCPConnection;
import jpize.util.net.tcp.TCPServer;
import jpize.util.net.tcp.packet.IPacket;
import jpize.util.net.tcp.packet.PacketDispatcher;
import jpize.util.net.tcp.packet.PacketHandler;
import jpize.util.security.AESKey;
import jpize.util.time.TimeUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpTests {

    @Test
    public void reconnect_client() {
        final int reconnectsNum = 7;
        final AtomicInteger counter = new AtomicInteger();
        new TCPServer()
                .setOnConnect((connection) -> counter.incrementAndGet())
                .setOnDisconnect((connection) -> counter.incrementAndGet())
                .run(65000);

        final TCPClient client = new TCPClient();
        for (int i = 0; i < reconnectsNum; i++){
            client.connect("localhost", 65000);
            client.disconnect();
        }
        TimeUtils.delayMillis(5000);
        TimeUtils.waitFor(() -> counter.get() == reconnectsNum * 2, 500, () -> Assert.fail(counter.get() + " / " + (reconnectsNum * 2)));
    }

    @Test
    public void disconnect_client() {
        final AtomicBoolean closed = new AtomicBoolean();
        new TCPServer()
                .setOnConnect(TCPConnection::close)
                .run(5406);
        new TCPClient()
                .setOnDisconnect((connection) -> closed.set(true))
                .connect("localhost", 5406);
        TimeUtils.waitFor(closed::get, 500, Assert::fail);
    }

    @Test
    public void close_server_connection() {
        final AtomicBoolean closed = new AtomicBoolean();
        new TCPServer()
                .setOnDisconnect((connection) -> closed.set(true))
                .run(5407);
        final TCPClient client = new TCPClient();
        client.connect("localhost", 5407);
        client.disconnect();
        TimeUtils.waitFor(closed::get, 500, Assert::fail);
    }

    @Test
    public void send_hello_world_to_server() {
        final String message = "Hello, World!";

        new TCPServer()
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
    }

    @Test
    public void send_hello_world_encrypted() {
        final AESKey key = new AESKey(128);
        final String message = "Hello, World!";

        new TCPServer()
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
    }

    public static void main(String[] args) throws Exception {
        // generate key
        final KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256);
        SecretKey key = generator.generateKey();
        // encrypt cipher
        final Cipher encryptCipher = Cipher.getInstance("AES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);
        // decrypt cipher
        final Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);

        // final
        final String message = "Secret message".repeat(10000000);
        for(int i = 0; i < 100; i++){
            final byte[] encryptedMessage = encryptCipher.doFinal(message.getBytes());
            final String decryptedMessage = new String(decryptCipher.doFinal(encryptedMessage));
            System.out.println(i + " message : success");
        }
    }

    @Test
    public void send_a_lot_of_data_encrypted() {
        final AESKey key = new AESKey(256);
        final String message = "0123456789".repeat(100000);

        final AtomicInteger counter = new AtomicInteger();
        final TCPServer server = new TCPServer();
        server.setOnConnect((connection) -> connection.setTcpNoDelay(true));
        server.setOnReceive((sender, bytes) -> {
            final String received = new String(bytes);
            System.out.println(counter.incrementAndGet());
            if(!message.equals(received)){
                Assert.fail();
                sender.close();
                System.err.println(received);
            }

            //Assert.assertEquals(message, received);
            //sender.close();
        });
        server.run(5406);

        final TCPClient client = new TCPClient();
        client.connect("localhost", 5406);
        client.connection().setTcpNoDelay(true);
        //client.encode(key);

        for(int i = 0; i < 100; i++)
            client.send(message.getBytes());

        TimeUtils.waitFor(client::isClosed);
    }

    @Test
    public void send_hello_world_to_client() {
        final String message = "Hello, World!";

        new TCPServer()
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
    }

    @Test
    public void send_a_lot_of_data_to_server() {
        final String message = "Hello, Data! ".repeat(100000);

        new TCPServer()
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
    }

    @Test
    public void connect_a_lot_of_clients_and_send_a_lot_of_data_multithreaded() {
        final String message = "Hello, World! ".repeat(10000);
        final int clientsAmount = 100;
        final AtomicInteger done = new AtomicInteger();

        final TCPServer server = new TCPServer();
        server.setOnReceive((sender, bytes) -> {
            final String received = new String(bytes);
            Assert.assertEquals(received, message);
            done.incrementAndGet();
        });
        server.run(5404);

        final List<TCPClient> clients = new CopyOnWriteArrayList<>();
        for(int i = 0; i < clientsAmount; i++){
            final TCPClient client = new TCPClient();
            client.connect("localhost", 5404);
            clients.add(client);
        }

        for(TCPClient client: clients)
            new Thread(() -> client.send(message.getBytes()).disconnect()).start();

        int prevDone = -1;
        while(done.get() != clientsAmount){
            if(done.get() != prevDone)
                prevDone = done.get();
            Thread.onSpinWait();
        }
    }


    @Test
    public void send_packet() {
        final String message = "Hello, World!";

        final PacketDispatcher dispatcher = new PacketDispatcher()
                .register(MsgPacket.class);

        final AtomicInteger counter = new AtomicInteger();
        final MsgHandler handler = (received) -> {
            Assert.assertEquals(message, received);
            counter.incrementAndGet();
        };

        new TCPServer()
            .setOnReceive((sender, bytes) -> {
                dispatcher.readPacket(bytes, handler);
                dispatcher.handlePackets();
            }).run(5403);

        final TCPClient client = new TCPClient();
        client.connect("localhost", 5403);
        client.send(new MsgPacket(message));
        client.send(new MsgPacket(message));

        TimeUtils.waitFor(() -> (counter.get() == 2), 2000, Assert::fail);
    }

    interface MsgHandler extends PacketHandler {
        void acceptMsg(String message);
    }

    static class MsgPacket extends IPacket<MsgHandler> {
        private String message;
        public MsgPacket(String message) {
            this.message = message;
        }
        public MsgPacket() { }
        public void write(ExtDataOutputStream stream) throws IOException {
            stream.writeStringBytes(message);
        }
        public void read(ExtDataInputStream stream) throws IOException {
            message = stream.readStringBytes();
        }
        public void handle(MsgHandler handler) {
            handler.acceptMsg(message);
        }
    }

}
