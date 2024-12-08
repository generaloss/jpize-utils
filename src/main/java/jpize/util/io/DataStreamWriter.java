package jpize.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface DataStreamWriter {

    void write(ExtDataOutputStream stream) throws IOException;

    static byte[] writeBytes(DataStreamWriter writer) {
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        try(final ExtDataOutputStream extStream = new ExtDataOutputStream(byteStream)){
            writer.write(extStream);
            return byteStream.toByteArray();
        }catch(IOException ignored) {
            return null;
        }
    }

}