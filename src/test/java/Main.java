import jpize.util.net.tcp.TCPClient;

public class Main {

    public static void main(String[] args) {
        new TCPClient()
            .setOnConnect(System.out::println)
            .connect("mineclone.ignorelist.com", 22854);
    }

}
