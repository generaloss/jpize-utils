package jpize.util.res;

import jpize.util.io.ExtDataOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExternalResource extends Resource {

    protected ExternalResource(File file) {
        super(file);
    }

    protected ExternalResource(String filepath) {
        super(new File(osGeneralizePath(filepath)));
    }

    protected ExternalResource(File parent, String child) {
        super(new File(parent, child));
    }


    @Override
    public InputStream inStream() {
        try{
            return new FileInputStream(super.file);
        }catch(FileNotFoundException e){
            throw new RuntimeException("File does not exists: " + super.file);
        }
    }

    @Override
    public boolean exists() {
        return super.file.exists();
    }

    @Override
    public Resource child(String name) {
        if(super.path().isEmpty())
            return new ExternalResource(name);

        return new ExternalResource(new File(super.file, name));
    }


    public boolean delete() {
        return super.file.delete();
    }

    public boolean create() {
        try{
            return super.file.createNewFile();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public boolean mkDirsAndFile() {
        mkParentDirs();
        return create();
    }

    public boolean mkParentDirs() {
        final File parentFile = super.file.getParentFile();
        if(parentFile != null)
            return parentFile.mkdirs();

        return false;
    }

    public boolean mkDirs() {
        return super.file.mkdirs();
    }

    public boolean mkDir() {
        return super.file.mkdir();
    }


    public void appendString(String string) {
        writeString(readString() + string);
    }

    public void writeString(String string) {
        try(final FileOutputStream out = outStream()){
            out.write(string.getBytes());
            out.flush();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void writeBytes(byte[] bytes) {
        try(final FileOutputStream out = outStream()){
            out.write(bytes);
            out.flush();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
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


    public PrintWriter writer() {
        return new PrintWriter(outStream(), true);
    }


    public ExtDataOutputStream extDataOutput() {
        return new ExtDataOutputStream(outStream());
    }

    public FileOutputStream outStream() {
        try{
            return new FileOutputStream(super.file);
        }catch(FileNotFoundException e){
            throw new RuntimeException("File does not exists: " + super.file);
        }
    }

}
