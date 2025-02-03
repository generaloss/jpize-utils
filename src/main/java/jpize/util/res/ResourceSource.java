package jpize.util.res;

@FunctionalInterface
public interface ResourceSource {

    Resource getResource(String path);

}
