package jpize.util.net.tcp;

import jpize.util.io.ExtDataInputStream;

import java.io.IOException;

@FunctionalInterface
public interface TcpStreamListener {

    void receive(TcpConnection sender, ExtDataInputStream stream) throws IOException;

}
