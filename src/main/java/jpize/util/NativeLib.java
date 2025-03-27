package jpize.util;

import jpize.util.res.Resource;
import jpize.util.res.TempFileResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class NativeLib {

    public static void load(Resource res) {
        try{
            final InputStream stream = res.inStream();
            final TempFileResource temp = Resource.temp(res.simpleName(), res.extension());
            temp.deleteOnExit();
            Files.copy(stream, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.load(temp.absolutePath());
        }catch(IOException e){
            throw new RuntimeException("Failed to load native library", e);
        }
    }

    public static String getFilename(String libname) {
        final String osName = System.getProperty("os.name").toLowerCase();
        if(osName.contains("win"))
            return (libname + ".dll");
        if(osName.contains("mac"))
            return ("lib" + libname + ".dylib");
        return ("lib" + libname + ".so");
    }

}
