package jpize.util.res.handle;

import jpize.util.res.Resource;

@FunctionalInterface
public interface IResourceSource {

    Resource getResource(String path);

}
