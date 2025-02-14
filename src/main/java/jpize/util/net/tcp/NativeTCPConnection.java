package jpize.util.net.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

public class NativeTCPConnection extends TCPConnection {

    private final ByteBuffer dataBuffer;

    protected NativeTCPConnection(SocketChannel channel, SelectionKey selectionKey, Consumer<TCPConnection> onDisconnect) {
        super(channel, selectionKey, onDisconnect);
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
            if(length == -1) // connection was closed by the other side
                super.close();
            if(bytes.size() == 0) // nothing to return
                return null;
            return super.tryToDecryptBytes(bytes.toByteArray());

        }catch(IOException ignored) {
            super.close();
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

        try{
            super.channel.write(buffer);
            if(buffer.hasRemaining()){
                writeQueue.add(buffer);
                selectionKey.interestOpsOr(SelectionKey.OP_WRITE);
                selectionKey.selector().wakeup();
            }
            return true;
        }catch(IOException e){

            super.close();
            return false;
        }
    }

}
