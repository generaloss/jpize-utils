package jpize.util.net;

import jpize.util.res.ExternalResource;

import java.io.*;
import java.net.URL;

public class NetUtils {

    public static void downloadToStream(URL url, OutputStream outStream, int bufferSize) throws IOException {
        final BufferedInputStream inStream = new BufferedInputStream(url.openStream());
        final byte[] buffer = new byte[bufferSize];

        while(!Thread.interrupted()){
            final int length = inStream.read(buffer, 0, bufferSize);
            if(length == -1)
                break;

            outStream.write(buffer, 0, length);
        }

        inStream.close();
    }

    public static void downloadToRes(String urlString, ExternalResource resource) {
        try{
            final FileOutputStream inStream = resource.outStream();
            downloadToStream(new URL(urlString), inStream, 1024);
            inStream.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void downloadToFile(String urlString, String filepath) {
        try{
            final FileOutputStream inStream = new FileOutputStream(filepath);
            downloadToStream(new URL(urlString), inStream, 1024);
            inStream.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void downloadToFile(String urlString, File file) {
        try{
            final FileOutputStream inStream = new FileOutputStream(file);
            downloadToStream(new URL(urlString), inStream, 1024);
            inStream.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


}
