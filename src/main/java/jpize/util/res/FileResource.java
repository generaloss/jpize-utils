package jpize.util.res;

import jpize.util.Utils;
import jpize.util.array.ObjectList;
import jpize.util.io.ExtDataOutputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileResource extends Resource {

    protected final File file;

    protected FileResource(File file) {
        this.file = file;
    }

    protected FileResource(String path) {
        this(new File(Utils.osGeneralizePath(path)));
    }

    protected FileResource(File parent, String child) {
        this(new File(parent, child));
    }

    public File file() {
        return file;
    }


    public boolean create() {
        try{
            return file.createNewFile();
        }catch(IOException ignored){
            return false;
        }
    }

    public boolean mkdir() {
        return file.mkdir();
    }

    public boolean mkdirs() {
        return file.mkdirs();
    }

    /** Creates all directories and file */
    public boolean mkAll() {
        final File parent = file.getParentFile();
        if(parent != null){
            parent.mkdirs();
            return this.create();
        }
        return false;
    }


    public boolean delete() {
        return file.delete();
    }

    public void deleteOnExit() {
        file.deleteOnExit();
    }


    public boolean isDir() {
        return file.isDirectory();
    }

    public boolean isFile() {
        return file.isFile();
    }


    public FileResource child(String name) {
        if(this.path().isEmpty())
            return new FileResource(name);
        return new FileResource(new File(file, name));
    }


    public FileOutputStream outStream() {
        try{
            return new FileOutputStream(file);
        }catch(FileNotFoundException e){
            throw new RuntimeException("Unable to open file " + e.getMessage());
        }
    }

    public ExtDataOutputStream outStreamExt() {
        return new ExtDataOutputStream(this.outStream());
    }

    public PrintWriter writer(boolean autoFlush) {
        return new PrintWriter(this.outStream(), autoFlush);
    }

    public PrintWriter writer() {
        return this.writer(true);
    }


    public void writeBytes(byte[] bytes) {
        try(final FileOutputStream out = this.outStream()){
            out.write(bytes);
            out.flush();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void writeString(String string, Charset charset) {
        this.writeBytes(string.getBytes(charset));
    }

    public void writeString(String string) {
        this.writeString(string, StandardCharsets.UTF_8);
    }

    public void appendString(String string, Charset charset) {
        this.writeString(this.readString() + string);
    }

    public void appendString(String string) {
        this.appendString(string, StandardCharsets.UTF_8);
    }


    public String[] list() {
        final String[] list = file.list();
        if(list == null)
            return new String[0];
        return list;
    }

    public String[] list(FilenameFilter filter) {
        final String[] list = file.list(filter);
        if(list == null)
            return new String[0];
        return list;
    }

    public FileResource[] listResources() {
        final String[] list = this.list();

        final FileResource[] resources = new FileResource[list.length];
        for(int i = 0; i < list.length; i++)
            resources[i] = this.child(list[i]);

        return resources;
    }

    public FileResource[] listResources(FilenameFilter filter) {
        final String[] list = file.list(filter);
        if(list == null)
            return new FileResource[0];

        final ObjectList<FileResource> resources = new ObjectList<>(list.length);

        for(String name: list)
            if(filter.accept(file, name))
                resources.add(this.child(name));

        return resources.arrayTrimmed();
    }


    public String absolutePath() {
        return Utils.osGeneralizePath(file.getAbsolutePath());
    }


    @Override
    public String name() {
        return file.getName();
    }

    @Override
    public String path() {
        return Utils.osGeneralizePath(file.getPath());
    }

    @Override
    public InputStream inStream() {
        try{
            return new FileInputStream(file);
        }catch(FileNotFoundException e){
            throw new RuntimeException("Unable to open file " + e.getMessage());
        }
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

}
