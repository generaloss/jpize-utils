package jpize.util.res.handle;

import jpize.util.Disposable;
import jpize.util.res.IResourceSource;

public abstract class ResHandle<K, H> implements Disposable {

    private final K key;
    private final String path;

    public ResHandle(K key, String path) {
        this.key = key;
        this.path = path;
    }

    public K getKey() {
        return key;
    }

    public String getPath() {
        return path;
    }


    abstract public void load(IResourceSource source, String path);

    abstract public H resource();

}
