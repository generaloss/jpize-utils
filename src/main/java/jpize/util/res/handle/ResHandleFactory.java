package jpize.util.res.handle;

@FunctionalInterface
public interface ResHandleFactory<K, H extends ResHandle<K, ?>> {

    H create(K key, String path);

}
