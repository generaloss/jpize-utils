package jpize.util.res;

import jpize.util.array.StringList;
import jpize.util.io.ExtDataInputStream;
import jpize.util.io.FastReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public abstract class Resource {

    protected Resource() { }


    public boolean isExternal() {
        return this instanceof ExternalResource;
    }

    public boolean isInternal() {
        return this instanceof InternalResource;
    }

    public boolean isUrl() {
        return this instanceof InternalResource;
    }

    public boolean isZipEntry() {
        return this instanceof ZipResource;
    }

    public ExternalResource asExternal() {
        return (ExternalResource) this;
    }

    public InternalResource asInternal() {
        return (InternalResource) this;
    }

    public URLResource asUrl() {
        return (URLResource) this;
    }

    public ZipResource asZipEntry() {
        return (ZipResource) this;
    }


    public byte[] readBytes() {
        try(InputStream inStream = this.inStream()){
            return inStream.readAllBytes();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public String readString() {
        return new String(this.readBytes());
    }

    public ByteBuffer readByteBuffer() {
        final byte[] bytes = this.readBytes();

        final ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length).order(ByteOrder.nativeOrder());
        buffer.put(bytes);
        buffer.flip();

        return buffer;
    }

    public StringList readLines() {
        final StringList lines = new StringList();

        final FastReader reader = this.reader();
        while(reader.hasNext())
            lines.add(reader.nextLine());
        reader.close();

        return lines.trim();
    }


    public FastReader reader() {
        return new FastReader(this.inStream());
    }


    public ExtDataInputStream extDataInput() {
        return new ExtDataInputStream(this.inStream());
    }


    public String name() {
        final String path = this.path();
        final int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex == -1) ? path : path.substring(separatorIndex + 1);
    }

    public String extension() {
        final String name = this.name();
        final int dotIndex = name.lastIndexOf(".");
        return (dotIndex == -1) ? "" : name.substring(dotIndex + 1);
    }

    public String simpleName() {
        final String name = this.name();
        final int dotIndex = name.lastIndexOf('.');
        return (dotIndex == -1) ? name : name.substring(0, dotIndex);
    }


    public abstract String path();

    public abstract InputStream inStream();

    public abstract boolean exists();


    @Override
    public String toString() {
        return this.path();
    }


    public static ExternalResource external(String path) {
        return new ExternalResource(path);
    }

    public static ExternalResource external(File file) {
        return new ExternalResource(file);
    }

    public static ExternalResource external(File parent, String child) {
        return new ExternalResource(parent, child);
    }

    public static InternalResource internal(String path) {
        return new InternalResource(path);
    }

    public static InternalResource internal(Class<?> classLoader, String path) {
        return new InternalResource(classLoader, path);
    }


    public static URLResource url(URL url) {
        return new URLResource(url);
    }

    public static URLResource url(String url) {
        return new URLResource(url);
    }


    public static ZipResource zip(ZipFile zipFile, ZipEntry entry) {
        return new ZipResource(zipFile, entry);
    }


    public static ExternalResource[] external(String... filepaths) {
        final ExternalResource[] arr = new ExternalResource[filepaths.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = external(filepaths[i]);
        return arr;
    }

    public static ExternalResource[] external(File... files) {
        final ExternalResource[] arr = new ExternalResource[files.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = external(files[i]);
        return arr;
    }

    public static InternalResource[] internal(String... paths) {
        final InternalResource[] arr = new InternalResource[paths.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = internal(paths[i]);
        return arr;
    }

    public static InternalResource[] internal(Class<?> classLoader, String... paths) {
        final InternalResource[] arr = new InternalResource[paths.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = internal(classLoader, paths[i]);
        return arr;
    }

    public static URLResource[] url(URL... urls) {
        final URLResource[] arr = new URLResource[urls.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = url(urls[i]);
        return arr;
    }

    public static URLResource[] url(String... urls) {
        final URLResource[] arr = new URLResource[urls.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = url(urls[i]);
        return arr;
    }

    public static ZipResource[] zip(ZipFile zipFile) {
        final ZipResource[] arr = new ZipResource[zipFile.size()];
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        for(int i = 0; entries.hasMoreElements(); i++)
            arr[i] = zip(zipFile, entries.nextElement());
        return arr;
    }

}