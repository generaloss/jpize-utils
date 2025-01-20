package jpize.util.net.tcp;

import jpize.util.io.ExtDataInputStream;

@FunctionalInterface
public interface TCPListenerStream {

    void receive(TCPConnection sender, ExtDataInputStream stream);

}
