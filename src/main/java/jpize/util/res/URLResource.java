package jpize.util.res;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URLResource extends Resource {

    protected final URL url;

    protected URLResource(URL url) {
        this.url = url;
    }

    protected URLResource(String url) {
        try{
            this.url = new URL(url);
        }catch(MalformedURLException e){
            throw new RuntimeException(e);
        }
    }


    public URL url() {
        return url;
    }

    public String protocol() {
        return url.getProtocol();
    }

    public String host() {
        return url.getHost();
    }


    @Override
    public String path() {
        return url.toExternalForm();
    }

    @Override
    public InputStream inStream() {
        try{
            final InputStream inStream = url.openStream();
            if(inStream == null)
                throw new RuntimeException("Remote resource does not exists: " + url);

            return inStream;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean exists() {
        try{
            url.openStream().close();
            return true;
        }catch(IOException ignored){
            return false;
        }
    }

}
