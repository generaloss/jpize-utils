package jpize.util.res;

import jpize.util.Utils;
import jpize.util.io.ExtDataOutputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileResource extends Resource {

    protected final File file;

    protected FileResource(File file) {
        this.file = file;
    }

    protected FileResource(Path path) {
        this(path.toFile());
    }

    protected FileResource(String path) {
        this(Path.of(Utils.osGeneralizePath(path)).normalize());
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


    public boolean renameTo(File dst) {
        return file.renameTo(dst);
    }

    public boolean renameTo(FileResource dst) {
        return this.renameTo(dst.file);
    }

    public FileResource rename(String name, boolean overwrite) {
        final File dst = new File(file.getParentFile(), name);
        if(!overwrite && dst.exists() || !this.renameTo(dst))
            return null;
        return new FileResource(dst);
    }

    public FileResource rename(String name) {
        return this.rename(name, false);
    }


    public FileResource move(Path target, CopyOption... options) {
        try{
            target = target.normalize();
            Files.move(file.toPath(), target, options);
            return new FileResource(target);
        }catch(IOException e){
            throw new RuntimeException("Cannot move file '" + file + "' to '" + target + "'");
        }
    }

    public FileResource move(String targetPath, CopyOption... options) {
        return this.move(Path.of(targetPath), options);
    }


    public FileResource copy(Path target, CopyOption... options) {
        try{
            target = target.normalize();
            Files.copy(file.toPath(), target, options);
            return new FileResource(target);
        }catch(IOException e){
            throw new RuntimeException("Cannot copy file '" + file + "' to '" + target + "'");
        }
    }

    public FileResource copy(String targetPath, CopyOption... options) {
        return this.copy(Path.of(targetPath), options);
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


    public String parentPath() {
        return file.getParent();
    }

    public File parentFile() {
        return file.getParentFile();
    }

    public FileResource parent() {
        return new FileResource(this.parentFile());
    }


    public File childFile(String name) {
        if(this.path().isEmpty())
            return new File(name);
        return new File(file, name);
    }

    public FileResource child(String name) {
        return new FileResource(this.childFile(name));
    }

    public FileResource createChildFile(String name) {
        final FileResource child = this.child(name);
        if(!child.mkAll())
            return null;
        return child;
    }

    public FileResource createChildDir(String name) {
        final FileResource child = this.child(name);
        if(!child.mkdirs())
            return null;
        return child;
    }


    public FileOutputStream outStream() {
        try{
            return new FileOutputStream(file);
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public ExtDataOutputStream outStreamExt() {
        return new ExtDataOutputStream(this.outStream());
    }


    public PrintWriter writer(boolean autoFlush, Charset charset) {
        return new PrintWriter(this.outStream(), autoFlush, charset);
    }

    public PrintWriter writer(boolean autoFlush) {
        return this.writer(autoFlush, Charset.defaultCharset());
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

        final List<FileResource> resources = new ArrayList<>(list.length);

        for(String name: list)
            if(filter.accept(file, name))
                resources.add(this.child(name));

        return resources.toArray(new FileResource[0]);
    }


    public String absolutePath() {
        return Utils.osGeneralizePath(file.getAbsolutePath());
    }

    public Path toPath() {
        return file.toPath();
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

}
