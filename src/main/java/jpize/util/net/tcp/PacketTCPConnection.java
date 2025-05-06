package jpize.util.net.tcp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class PacketTCPConnection extends TCPConnection {

    private final ByteBuffer lengthBuffer;
    private ByteBuffer dataBuffer;

    protected PacketTCPConnection(SocketChannel channel, SelectionKey selectionKey, TCPCloseable onClose) {
        super(channel, selectionKey, onClose);
        this.lengthBuffer = ByteBuffer.allocate(4);
    }

    @Override
    protected byte[] read() {
        try{
            if(lengthBuffer.hasRemaining()){
                final int bytesRead = super.channel.read(lengthBuffer);
                if(bytesRead == -1){
                    // connection closed
                    this.close(TCPCloseable.CONNECTION_CLOSED);
                    return null;
                }
                if(lengthBuffer.hasRemaining())
                    return null; // partial read, keep the channel open

                // allocate data buffer
                lengthBuffer.flip();
                final int length = lengthBuffer.getInt();
                dataBuffer = ByteBuffer.allocate(length);
            }

            // read data
            final int bytesRead = super.channel.read(dataBuffer);
            if(bytesRead == -1){
                // connection closed
                this.close(TCPCloseable.CONNECTION_CLOSED);
                return null;
            }

            if(dataBuffer.hasRemaining()){
                // not done reading
                return null;
            }else{
                // reset length buffer for next message
                lengthBuffer.clear();
                // process the message
                dataBuffer.flip();
                // decrypt
                final byte[] data = new byte[dataBuffer.remaining()];
                dataBuffer.get(data);
                return super.tryToDecryptBytes(data);
            }
        }catch(IOException e){
            this.close(e.getMessage());
            return null;
        }
    }


    @Override
    public boolean send(byte[] bytes) {
        if(super.isClosed())
            return false;

        bytes = this.tryToEncryptBytes(bytes);

        final ByteBuffer buffer = ByteBuffer.allocate(4 + bytes.length);
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        return super.toWriteQueue(buffer);
    }

}
