package jpize.util.net.tcp;

import jpize.util.io.ExtDataInputStream;

@FunctionalInterface
public interface TcpStreamListener {

    void receive(TcpConnection sender, ExtDataInputStream stream);

}
