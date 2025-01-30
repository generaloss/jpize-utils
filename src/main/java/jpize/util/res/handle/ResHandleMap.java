package jpize.util.res.handle;

import jpize.util.Disposable;
import jpize.util.res.IResourceSource;

import java.util.HashMap;
import java.util.Map;

public class ResHandleMap<K, H extends ResHandle<K, ?>> implements Disposable {

    private IResHandleFactory<K, H> handleFactory;
    private IResourceSource source;
    private final Map<K, H> map;

    public ResHandleMap() {
        this.map = new HashMap<>();
    }

    public ResHandleMap(IResourceSource source) {
        this();
        this.setSource(source);
    }

    public ResHandleMap(IResHandleFactory<K, H> handleFactory) {
        this();
        this.setHandleFactory(handleFactory);
    }

    public ResHandleMap(IResourceSource source, IResHandleFactory<K, H> handleFactory) {
        this();
        this.setSource(source);
        this.setHandleFactory(handleFactory);
    }


    public IResHandleFactory<K, H> getHandleFactory() {
        return handleFactory;
    }

    public void setHandleFactory(IResHandleFactory<K, H> handleFactory) {
        this.handleFactory = handleFactory;
    }


    public IResourceSource getSource() {
        return source;
    }

    public void setSource(IResourceSource source) {
        this.source = source;
    }


    public int size() {
        return map.size();
    }


    public H get(K key) {
        return map.get(key);
    }

    public void dispose(H handle) {
        if(handle == null)
            return;

        handle.dispose();
        map.remove(handle.getKey());
    }

    public void dispose(K key) {
        this.dispose(map.get(key));
    }

    public H create(K key, String path) {
        if(source == null)
            throw new IllegalStateException("Resource source not set");
        if(handleFactory == null)
            throw new IllegalStateException("Resource handle factory not set");

        final H handle = handleFactory.create(key, path);
        handle.load(source, path);
        map.put(key, handle);

        return handle;
    }

    public H create(H handle) {
        if(source == null)
            throw new IllegalStateException("Resource source not set");

        handle.load(source, handle.getPath());
        map.put(handle.getKey(), handle);
        return handle;
    }

    public void reload() {
        if(source == null)
            throw new IllegalStateException("Resource source not set");

        map.forEach((key, handle) ->
            handle.load(source, handle.getPath()));
    }

    @Override
    public void dispose() {
        for(H handle: map.values())
            handle.dispose();
        map.clear();
    }

}
