package jpize.util.res.handle;

@FunctionalInterface
public interface IResourceHandleFactory<K, H extends ResourceHandle<K, ?>> {

    H create(K key, String path);

}
