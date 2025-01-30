package jpize.util.res.handle;

@FunctionalInterface
public interface IResHandleFactory<K, H extends ResHandle<K, ?>> {

    H create(K key, String path);

}
