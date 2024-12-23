package jpize.util.net.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

public class BufferedTCPConnection extends TCPConnection {

    private final ByteBuffer lengthBuffer;
    private ByteBuffer readBuffer;
    private int bytesRemaining;

    protected BufferedTCPConnection(SocketChannel channel, SelectionKey selectionKey, Consumer<TCPConnection> onDisconnect) {
        super(channel, selectionKey, onDisconnect);
        this.lengthBuffer = ByteBuffer.allocate(4); // 4 bytes for integer
    }


    private boolean isDataBufferFull() {
        return bytesRemaining == 0;
    }

    private boolean allocateDataBuffer() throws IOException {
        // try to read length
        final int length = super.channel.read(lengthBuffer);

        if(length == -1) { // connection was closed on the other side
            super.close();
            return false;
        }else if(length < 4) // not int size (length)
            return false;

        // read length
        lengthBuffer.flip();
        bytesRemaining = lengthBuffer.getInt();
        lengthBuffer.clear();

        // allocate buffer
        readBuffer = ByteBuffer.allocate(bytesRemaining);
        return true;
    }

    @Override
    protected byte[] read() {
        try{
            // if previous buffer is full allocate new buffer
            if(this.isDataBufferFull() && !this.allocateDataBuffer())
                return null;
            // read bytes and when the buffer is full accept them
            bytesRemaining -= super.channel.read(readBuffer);
            if(this.isDataBufferFull()){
                final byte[] bytes = this.tryToDecryptBytes(readBuffer.array());
                readBuffer.clear();
                return bytes;
            }
        }catch(IOException ignore){
            this.close();
        }
        return null;
    }

    @Override
    public void send(byte[] bytes) {
        if(super.isClosed())
            throw new IllegalStateException("TCP-connection is closed.");

        try{
            bytes = this.tryToEncryptBytes(bytes);

            final ByteBuffer buffer = ByteBuffer.allocate(4 + bytes.length);
            buffer.putInt(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            super.channel.write(buffer);
            buffer.clear();
        }catch(IOException e){
            super.close();
        }
    }

}
