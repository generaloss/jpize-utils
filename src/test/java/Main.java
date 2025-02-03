import jpize.util.io.ExtDataInputStream;
import jpize.util.io.ExtDataOutputStream;
import jpize.util.net.tcp.TCPClient;
import jpize.util.net.tcp.TCPServer;
import jpize.util.net.packet.NetPacket;
import jpize.util.net.packet.NetPacketDispatcher;
import jpize.util.time.TimeUtils;

import java.io.IOException;

public class Main {

    public static class PacketHandler {

        public void handleMessagePacket(MessagePacket packet) {
            // вывод в консоль данных из полученного пакета
            System.out.println(packet.getMessage());
        }

    }

    public static class MessagePacket extends NetPacket<PacketHandler> {

        private String message;

        public MessagePacket(String message) { // конструктор с данными для передачи
            this.message = message;
        }

        public MessagePacket() { } // пустой конструктор

        public String getMessage() {
            return message;
        }

        @Override
        public void write(ExtDataOutputStream stream) throws IOException {
            // записываем данные для отправки
            stream.writeUTFString(message);
        }

        @Override
        public void read(ExtDataInputStream stream) throws IOException {
            // читаем полученные данные
            message = stream.readUTFString();
        }

        @Override
        public void handle(PacketHandler handler) { // handle this packet
            // передаем пакет в обработчик
            handler.handleMessagePacket(this);
        }
    }

    public static void main(String[] args) {
        // create packet handler
        PacketHandler handler = new PacketHandler();
        // create packet dispatcher and register packets
        NetPacketDispatcher packetDispatcher = new NetPacketDispatcher();
        packetDispatcher.register(MessagePacket.class);//, AnotherPacket.class, ...);

        // create server and set receiver
        TCPServer server = new TCPServer();
        server.setOnReceive((sender, bytes) -> {
            packetDispatcher.readPacket(bytes, handler);
            packetDispatcher.handlePackets(); // invoke handleMessage()
            server.close();
        });
        server.run(65000);

        // create client and send packet
        TCPClient client = new TCPClient();
        client.connect("localhost", 65000);
        client.send(new MessagePacket("My message!"));

        TimeUtils.waitFor(client::isClosed);
    }

}
