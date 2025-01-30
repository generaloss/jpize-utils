package jpize.util.res;

@FunctionalInterface
public interface IResourceSource {

    Resource getResource(String path);

}
