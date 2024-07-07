package jpize.util.res;

import jpize.util.io.ExtDataInputStream;
import jpize.util.io.FastReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.List;

public abstract class Resource {

    protected final File file;

    public Resource(File file) {
        this.file = file;
    }


    public boolean isExternal() {
        return this instanceof ExternalResource;
    }

    public boolean isInternal() {
        return this instanceof InternalResource;
    }

    public ExternalResource asExternal() {
        return (ExternalResource) this;
    }

    public InternalResource asInternal() {
        return (InternalResource) this;
    }


    public byte[] readBytes() {
        try(InputStream inStream = inStream()){
            return inStream.readAllBytes();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public String readString() {
        return new String(readBytes());
    }

    public ByteBuffer readByteBuffer() {
        final byte[] bytes = readBytes();

        final ByteBuffer buffer =
            ByteBuffer.allocateDirect(bytes.length).order(ByteOrder.nativeOrder());
        buffer.put(bytes);
        buffer.flip();

        return buffer;
    }

    public List<String> readLines() {
        try{
            return Files.readAllLines(file.toPath());
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    public FastReader reader() {
        return new FastReader(inStream());
    }

    public File file() {
        return file;
    }


    public String name() {
        return file.getName();
    }

    public String extension() {
        final String name = name();
        final int dotIndex = name.lastIndexOf(".");
        return (dotIndex == -1) ? "" : name.substring(dotIndex + 1);
    }

    public String simpleName() {
        final String name = name();
        final int dotIndex = name.lastIndexOf('.');
        return (dotIndex == -1) ? name : name.substring(0, dotIndex);
    }

    public String path() {
        return osGeneralizePath(file.getPath());
    }

    public String absolutePath() {
        return file.getAbsolutePath();
    }


    public ExtDataInputStream extDataInput() {
        return new ExtDataInputStream(inStream());
    }

    public abstract InputStream inStream();

    public abstract boolean exists();

    public abstract Resource child(String name);


    @Override
    public String toString() {
        return name();
    }


    public static String osGeneralizePath(String path) {
        return path.replace("\\", "/");
    }


    public static ExternalResource external(String filepath) {
        return new ExternalResource(filepath);
    }

    public static ExternalResource external(File file) {
        return new ExternalResource(file);
    }

    public static ExternalResource external(File parent, String child) {
        return new ExternalResource(parent, child);
    }


    public static InternalResource internal(String filepath) {
        return new InternalResource(filepath);
    }

    public static InternalResource internal(File file) {
        return new InternalResource(file);
    }

    public static InternalResource internal(File parent, String child) {
        return new InternalResource(parent, child);
    }

    public static InternalResource internal(String filepath, Class<?> classLoader) {
        return new InternalResource(filepath, classLoader);
    }

    public static InternalResource internal(File file, Class<?> classLoader) {
        return new InternalResource(file, classLoader);
    }

    public static InternalResource internal(File parent, String child, Class<?> classLoader) {
        return new InternalResource(parent, child, classLoader);
    }

}