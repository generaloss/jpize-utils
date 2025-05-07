package jpize.util.net.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class NativeTCPConnection extends TCPConnection {

    private final ByteBuffer dataBuffer;

    protected NativeTCPConnection(SocketChannel channel, SelectionKey selectionKey, TCPCloseable onClose) {
        super(channel, selectionKey, onClose);
        this.dataBuffer = ByteBuffer.allocate(2048);
    }

    @Override
    protected byte[] read() {
        try{
            // read all available data
            final ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            int length = super.channel.read(dataBuffer);
            while(length > 0){
                dataBuffer.flip();
                bytes.write(dataBuffer.array(), 0, length);
                dataBuffer.clear();
                length = super.channel.read(dataBuffer);
            }

            if(length == -1) {
                // connection closed
                super.close("Connection closed on other side");
                return null;
            }
            if(bytes.size() == 0) // nothing to return
                return null;

            return super.tryToDecryptBytes(bytes.toByteArray());

        }catch(IOException e) {
            super.close(e);
            return null;
        }
    }

    @Override
    public boolean send(byte[] bytes) {
        if(super.isClosed())
            return false;

        bytes = this.tryToEncryptBytes(bytes);

        final ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        return super.toWriteQueue(buffer);
    }

}
