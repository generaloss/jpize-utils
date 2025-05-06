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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public abstract class Resource {

    protected Resource() { }


    public ExtDataInputStream inStreamExt() {
        return new ExtDataInputStream(this.inStream());
    }

    public FastReader reader() {
        return new FastReader(this.inStream());
    }


    public byte[] readBytes() {
        try(InputStream inStream = this.inStream()){
            return inStream.readAllBytes();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public ByteBuffer readByteBuffer() {
        final byte[] bytes = this.readBytes();

        final ByteBuffer buffer = ByteBuffer.allocateDirect(bytes.length);
        buffer.order(ByteOrder.nativeOrder());
        buffer.put(bytes);
        buffer.flip();

        return buffer;
    }

    public String readString(Charset charset) {
        return new String(this.readBytes(), charset);
    }

    public String readString() {
        return this.readString(StandardCharsets.UTF_8);
    }

    public StringList readLines(Charset charset) {
        final StringList lines = new StringList();

        final FastReader reader = this.reader();
        while(reader.hasNext())
            lines.add(reader.nextLine(charset));
        reader.close();

        return lines.trim();
    }

    public StringList readLines() {
        return this.readLines(StandardCharsets.UTF_8);
    }


    public String name() {
        final String path = this.path();
        final int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex == -1) ? path : path.substring(separatorIndex + 1);
    }

    public String simpleName() {
        final String name = this.name();
        final int dotIndex = name.lastIndexOf('.');
        return (dotIndex == -1) ? name : name.substring(0, dotIndex);
    }

    public String extension() {
        final String name = this.name();
        final int dotIndex = name.lastIndexOf(".");
        return (dotIndex == -1) ? "" : name.substring(dotIndex + 1);
    }


    public boolean isFileRes() {
        return (this instanceof FileResource);
    }

    public boolean isTempRes() {
        return (this instanceof TempFileResource);
    }

    public boolean isInternalRes() {
        return (this instanceof InternalResource);
    }

    public boolean isUrlRes() {
        return (this instanceof URLResource);
    }

    public boolean isZipRes() {
        return (this instanceof ZipResource);
    }


    public FileResource asFileRes() {
        return ((FileResource) this);
    }

    public TempFileResource asTempRes() {
        return ((TempFileResource) this);
    }

    public InternalResource asInternalRes() {
        return ((InternalResource) this);
    }

    public URLResource asUrlRes() {
        return ((URLResource) this);
    }

    public ZipResource asZipRes() {
        return ((ZipResource) this);
    }


    public abstract String path();

    public abstract InputStream inStream();

    public abstract boolean exists();


    @Override
    public String toString() {
        return this.path();
    }


    public static FileResource file(String path) {
        return new FileResource(path);
    }

    public static FileResource file(File file) {
        return new FileResource(file);
    }

    public static FileResource file(Path path) {
        return new FileResource(path);
    }

    public static FileResource file(File parent, String child) {
        return new FileResource(parent, child);
    }

    public static FileResource[] file(String... paths) {
        final FileResource[] arr = new FileResource[paths.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = file(paths[i]);
        return arr;
    }

    public static FileResource[] file(File... files) {
        final FileResource[] arr = new FileResource[files.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = file(files[i]);
        return arr;
    }

    public static FileResource[] file(Path... paths) {
        final FileResource[] arr = new FileResource[paths.length];
        for(int i = 0; i < arr.length; i++)
            arr[i] = file(paths[i]);
        return arr;
    }


    public static TempFileResource temp(String prefix, String suffix, File directory) {
        return new TempFileResource(prefix, suffix, directory);
    }

    public static TempFileResource temp(String prefix, String suffix) {
        return new TempFileResource(prefix, suffix);
    }


    public static InternalResource internal(String path) {
        return new InternalResource(path);
    }

    public static InternalResource internal(Class<?> classLoader, String path) {
        return new InternalResource(classLoader, path);
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


    public static URLResource url(URL url) {
        return new URLResource(url);
    }

    public static URLResource url(String url) {
        return new URLResource(url);
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


    public static ZipResource zip(ZipFile zipFile, ZipEntry entry) {
        return new ZipResource(zipFile, entry);
    }

    public static ZipResource[] zip(ZipFile zipFile) {
        final ZipResource[] arr = new ZipResource[zipFile.size()];
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        for(int i = 0; entries.hasMoreElements(); i++)
            arr[i] = zip(zipFile, entries.nextElement());
        return arr;
    }

}