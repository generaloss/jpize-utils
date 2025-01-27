package jpize.util.res.handle;

import jpize.util.Disposable;

import java.util.HashMap;
import java.util.Map;

public class ResourceHandleMap<K, H extends ResourceHandle<K, ?>> implements Disposable {

    private IResourceHandleFactory<K, H> handleFactory;
    private IResourceSource source;
    private final Map<K, H> map;

    public ResourceHandleMap() {
        this.map = new HashMap<>();
    }

    public ResourceHandleMap(IResourceSource source) {
        this();
        this.setSource(source);
    }

    public ResourceHandleMap(IResourceHandleFactory<K, H> handleFactory) {
        this();
        this.setHandleFactory(handleFactory);
    }

    public ResourceHandleMap(IResourceSource source, IResourceHandleFactory<K, H> handleFactory) {
        this();
        this.setSource(source);
        this.setHandleFactory(handleFactory);
    }


    public IResourceHandleFactory<K, H> getHandleFactory() {
        return handleFactory;
    }

    public void setHandleFactory(IResourceHandleFactory<K, H> handleFactory) {
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

    public void remove(H handle) {
        if(handle == null)
            return;

        handle.dispose();
        map.remove(handle.getKey());
    }

    public void remove(K key) {
        this.remove(map.get(key));
    }

    public H load(K key, String path) {
        if(source == null)
            throw new IllegalStateException("Resource source not set");
        if(handleFactory == null)
            throw new IllegalStateException("Resource handle factory not set");

        final H handle = handleFactory.create(key, path);
        handle.load(source, path);
        map.put(key, handle);

        return handle;
    }

    public H load(H handle) {
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
