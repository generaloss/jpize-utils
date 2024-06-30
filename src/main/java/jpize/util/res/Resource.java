package jpize.util.res;

import jpize.util.io.ExtDataInputStream;
import jpize.util.io.FastReader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.ArrayList;
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


    public String readString() {
        try(final InputStream in = inStream()){
            return new String(in.readAllBytes());
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public byte[] readBytes() {
        try(InputStream inStream = inStream()){
            return inStream.readAllBytes();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
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


    public String[] list() {
        return file.list();
    }

    public String[] list(FilenameFilter filter) {
        return file.list(filter);
    }

    public Resource[] listRes() {
        final String[] paths = file.list();
        if(paths == null)
            return new Resource[0];

        final Resource[] resources = new Resource[paths.length];
        for(int i = 0; i < paths.length; i++)
            resources[i] = child(paths[i]);

        return resources;
    }

    public Resource[] listRes(FilenameFilter filter) {
        final Resource[] resources = listRes();
        final List<Resource> filteredResources = new ArrayList<>();

        for(Resource resource: resources)
            if(filter.accept(file, resource.name()))
                filteredResources.add(resource);

        return filteredResources.toArray(new Resource[0]);
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


    static String osGeneralizePath(String path) {
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