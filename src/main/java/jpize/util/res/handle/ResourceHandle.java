package jpize.util.res.handle;

import jpize.util.Disposable;

public abstract class ResourceHandle<K, H> implements Disposable {

    private final K key;
    private final String path;

    public ResourceHandle(K key, String path) {
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
