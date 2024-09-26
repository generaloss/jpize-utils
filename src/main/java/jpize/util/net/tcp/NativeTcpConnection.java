package jpize.util.net.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

public class NativeTcpConnection extends TcpConnection {

    private final ByteBuffer readBuffer;

    protected NativeTcpConnection(SocketChannel channel, SelectionKey selectionKey, Consumer<TcpConnection> onDisconnect) {
        super(channel, selectionKey, onDisconnect);
        this.readBuffer = ByteBuffer.allocate(2048);
    }

    @Override
    protected byte[] read() {
        try{
            // read all available data
            final ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            int length = super.channel.read(readBuffer);
            while(length > 0){
                readBuffer.flip();
                bytes.write(readBuffer.array(), 0, length);
                readBuffer.clear();
                length = super.channel.read(readBuffer);
            }
            if(length == -1) // connection was closed on the other side
                super.close();
            if(bytes.size() == 0) // nothing to return
                return null;
            return this.tryToDecryptBytes(bytes.toByteArray());
        }catch(IOException ignored) {
            super.close();
        }
        return null;
    }

    @Override
    public void send(byte[] bytes) {
        if(super.isClosed())
            throw new IllegalStateException("TCP-connection is closed.");

        try{
            bytes = this.tryToEncryptBytes(bytes);

            final ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            super.channel.write(buffer);
            buffer.clear();
        }catch(IOException e){
            super.close();
        }
    }

}
