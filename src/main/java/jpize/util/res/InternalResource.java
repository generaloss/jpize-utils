package jpize.util.res;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class InternalResource extends Resource {

    private final Class<?> classLoader;


    protected InternalResource(String filepath, Class<?> classLoader) {
        super(new File(filepath));
        this.classLoader = classLoader;
    }

    protected InternalResource(File file, Class<?> classLoader) {
        super(file);
        this.classLoader = classLoader;
    }

    protected InternalResource(File parent, String child, Class<?> classLoader) {
        this(new File(parent, child), classLoader);
    }

    protected InternalResource(String filepath) {
        this(filepath, InternalResource.class);
    }

    protected InternalResource(File file) {
        this(file, InternalResource.class);
    }

    protected InternalResource(File parent, String child) {
        this(new File(parent, child), InternalResource.class);
    }


    @Override
    public InputStream inStream() {
        try{
            final InputStream inStream = classLoader.getResourceAsStream("/" + super.path());
            if(inStream == null)
                throw new FileNotFoundException("Internal file does not exists: " + super.path());

            return inStream;

        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean exists() {
        return classLoader.getResource("/" + super.path()) != null;
    }

    @Override
    public Resource child(String name) {
        if(super.path().isEmpty())
            return new InternalResource(name, classLoader);

        return new InternalResource(new File(super.file, name), classLoader);
    }


    public String resName() {
        try(InputStream in = inStream()){
            return in.toString();
        }catch(IOException e){
            return null;
        }
    }

}
