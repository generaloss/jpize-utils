package jpize.util.res;

import java.io.File;
import java.io.IOException;

public class TempFileResource extends FileResource {

    private static File createTempFile(String prefix, String suffix, File directory) {
        try{
            return File.createTempFile(prefix, suffix, directory);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    protected TempFileResource(String prefix, String suffix, File directory) {
        super(createTempFile(prefix, suffix, directory));
    }

    protected TempFileResource(String prefix, String suffix) {
        this(prefix, suffix, null);
    }

}
