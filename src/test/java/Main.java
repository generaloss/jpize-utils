import jpize.util.io.FastReader;

public class Main {

    public static void main(String[] args) {
        // new TCPClient()
        //     .setOnConnect(System.out::println)
        //     .connect("mineclone.ignorelist.com", 22854);

        // final NewFastReader reader = new NewFastReader(System.in);
        final FastReader reader = new FastReader(System.in);
        while(!Thread.interrupted()){
            reader.waitNext();
            System.out.println(reader.nextLine());
        }
    }

}
