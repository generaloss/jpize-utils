import jpize.util.net.tcp.TCPClient;
import jpize.util.time.TimeUtils;

public class Main {

    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        System.out.println("Connecting...");
        client.connect("mineclone.ignorelist.com", 22854, 100L);
        TimeUtils.waitFor(client::isConnected);
        System.out.println("Connected!");
        client.disconnect();
    }

}
