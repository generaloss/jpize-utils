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

    public Resource() { }


    public boolean isExternal() {
        return this instanceof ExternalResource;
    }

    public boolean isInternal() {
        return this instanceof InternalResource;
    }

    public boolean isUrl() {
        return this instanceof InternalResource;
    }

    public ExternalResource asExternal() {
        return (ExternalResource) this;
    }

    public InternalResource asInternal() {
        return (InternalResource) this;
    }

    public UrlResource asUrl() {
        return (UrlResource) this;
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
        final StringList result = new StringList();
        final FastReader reader = this.reader();

        while(reader.hasNext()) {
            final String line = reader.nextLine();
            result.add(line);
        }
        reader.close();

        if(result.getLast().isEmpty())
            result.removeLast();
        result.trim();
        return result;
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


    public static UrlResource url(URL url) {
        return new UrlResource(url);
    }

    public static UrlResource url(String url) {
        return new UrlResource(url);
    }


    public static ZipEntryResource zipEntry(ZipFile zipFile, ZipEntry entry) {
        return new ZipEntryResource(zipFile, entry);
    }


    public static ExternalResource[] external(String... filepath) {
        final ExternalResource[] arr = new ExternalResource[filepath.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = external(filepath[i]);
        return arr;
    }

    public static ExternalResource[] external(File... file) {
        final ExternalResource[] arr = new ExternalResource[file.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = external(file[i]);
        return arr;
    }

    public static InternalResource[] internal(String... name) {
        final InternalResource[] arr = new InternalResource[name.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = internal(name[i]);
        return arr;
    }

    public static InternalResource[] internal(Class<?> classLoader, String... name) {
        final InternalResource[] arr = new InternalResource[name.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = internal(classLoader, name[i]);
        return arr;
    }

    public static UrlResource[] url(URL... url) {
        final UrlResource[] arr = new UrlResource[url.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = url(url[i]);
        return arr;
    }

    public static UrlResource[] url(String... url) {
        final UrlResource[] arr = new UrlResource[url.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = url(url[i]);
        return arr;
    }

    public static ZipEntryResource[] zipEntry(ZipFile zipFile) {
        final ZipEntryResource[] arr = new ZipEntryResource[zipFile.size()];
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        for(int i = 0; entries.hasMoreElements(); i++)
            arr[i] = zipEntry(zipFile, entries.nextElement());
        return arr;
    }

}