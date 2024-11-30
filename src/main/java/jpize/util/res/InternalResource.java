package jpize.util.res;

import jpize.util.Utils;
import java.io.InputStream;

public class InternalResource extends Resource {

    protected final String path;
    protected final Class<?> classLoader;

    protected InternalResource(Class<?> classLoader, String path) {
        this.classLoader = classLoader;
        this.path = Utils.osGeneralizePath(path);
    }

    protected InternalResource(String path) {
        this(InternalResource.class, path);
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public InputStream inStream() {
        final InputStream stream = classLoader.getResourceAsStream(path);
        if(stream == null)
            throw new RuntimeException("Internal resource does not exists: " + path);
        return stream;
    }

    @Override
    public boolean exists() {
        return classLoader.getResource(path) != null;
    }

}
