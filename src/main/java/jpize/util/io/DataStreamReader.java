package jpize.util.io;

import java.io.IOException;

public interface DataStreamReader {

    void read(ExtDataInputStream stream) throws IOException;

}
