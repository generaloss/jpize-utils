package jpize.util.res;

import jpize.util.Utils;
import jpize.util.io.ExtDataOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExternalResource extends Resource {

    protected final File file;

    protected ExternalResource(File file) {
        this.file = file;
    }

    protected ExternalResource(String path) {
        this(new File(Utils.osGeneralizePath(path)));
    }

    protected ExternalResource(File parent, String child) {
        this(new File(parent, child));
    }

    public File file() {
        return file;
    }


    public ExternalResource child(String name) {
        if(this.path().isEmpty())
            return new ExternalResource(name);
        return new ExternalResource(new File(file, name));
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


    public boolean isDir() {
        return file.isDirectory();
    }

    public boolean isFile() {
        return file.isFile();
    }


    public void appendString(String string) {
        this.writeString(this.readString() + string);
    }

    public void writeString(String string) {
        try(final FileOutputStream out = this.outStream()){
            out.write(string.getBytes());
            out.flush();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void writeBytes(byte[] bytes) {
        try(final FileOutputStream out = this.outStream()){
            out.write(bytes);
            out.flush();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
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

    public ExternalResource[] listRes() {
        final String[] list = this.list();

        final ExternalResource[] resources = new ExternalResource[list.length];
        for(int i = 0; i < list.length; i++)
            resources[i] = this.child(list[i]);

        return resources;
    }

    public ExternalResource[] listRes(FilenameFilter filter) {
        final ExternalResource[] resources = this.listRes();
        final List<ExternalResource> filteredResources = new ArrayList<>();

        for(ExternalResource resource: resources)
            if(filter.accept(file, resource.name()))
                filteredResources.add(resource);

        return filteredResources.toArray(new ExternalResource[0]);
    }


    public PrintWriter writer() {
        return new PrintWriter(this.outStream(), true);
    }


    public ExtDataOutputStream extDataOutput() {
        return new ExtDataOutputStream(this.outStream());
    }

    public FileOutputStream outStream() {
        try{
            return new FileOutputStream(file);
        }catch(FileNotFoundException e){
            throw new RuntimeException("Unable to open file " + e.getMessage());
        }
    }


    public String name() {
        return file.getName();
    }

    public String absolutePath() {
        return Utils.osGeneralizePath(file.getAbsolutePath());
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
