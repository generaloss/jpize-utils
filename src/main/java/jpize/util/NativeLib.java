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
            if(getOsName().equals("android")) {
                final String libName = res.simpleName().replace("lib", "");
                System.loadLibrary(libName);
                return;
            }

            final InputStream stream = res.inStream();
            final TempFileResource temp = Resource.temp(res.simpleName(), res.extension());
            temp.deleteOnExit();
            Files.copy(stream, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.load(temp.absolutePath());
        }catch(IOException e){
            throw new RuntimeException("Failed to load native library", e);
        }
    }

    public static String getOsName() {
        final String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
            return "windows";

        if(os.contains("linux")) {
            final String runtimeName = System.getProperty("java.runtime.name", "").toLowerCase();
            if(runtimeName.contains("android"))
                return "android";
            return "linux";
        }
        return os;
    }

    public static String getFilename(String libname) {
        final String os = getOsName();
        if(os.contains("win"))
            return (libname + ".dll");
        if(os.contains("mac"))
            return ("lib" + libname + ".dylib");
        return ("lib" + libname + ".so");
    }

}
