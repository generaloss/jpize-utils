package jpize.util.res;

import jpize.util.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipEntryResource extends Resource {

    protected final ZipFile file;
    protected final ZipEntry entry;

    private List<ZipEntry> entryList;
    private String[] stringList;
    private ZipEntryResource[] resourceList;

    protected ZipEntryResource(ZipFile file, ZipEntry entry) {
        this.file = file;
        this.entry = entry;
    }

    public ZipFile file() {
        return file;
    }

    public ZipEntry entry() {
        return entry;
    }


    public boolean isDir() {
        return entry.isDirectory();
    }

    public boolean isFile() {
        return !entry.isDirectory();
    }


    private void cacheList() {
        if(entryList != null)
            return;
        entryList = new ArrayList<>();
        final Enumeration<? extends ZipEntry> entries = file.entries();

        while(entries.hasMoreElements()) {
            final ZipEntry entry = entries.nextElement();
            if(entry.isDirectory() || entry == this.entry)
                continue;
            if(entry.getName().startsWith(this.entry.getName()))
                entryList.add(entry);
        }

        if(stringList != null)
            return;
        stringList = new String[entryList.size()];
        for(int i = 0; i < stringList.length; i++)
            stringList[i] = entryList.get(i).getName();
    }

    private void cacheResourceList() {
        if(resourceList != null)
            return;
        this.cacheList();
        resourceList = new ZipEntryResource[entryList.size()];
        for(int i = 0; i < resourceList.length; i++)
            resourceList[i] = new ZipEntryResource(file, entryList.get(i));
    }

    public String[] list() {
        if(this.isFile())
            throw new IllegalStateException("File cannot be listed.");
        this.cacheList();
        return stringList;
    }

    public ZipEntryResource[] listRes() {
        this.cacheResourceList();
        return resourceList;
    }


    public String name() {
        if(this.isDir())
            return entry.getName();
        final String name = entry.getName();
        return name.substring(name.lastIndexOf('/') + 1);
    }

    public String absolutePath() {
        return Utils.osGeneralizePath(file.getName()) + "/" + this.path();
    }


    @Override
    public String path() {
        return Utils.osGeneralizePath(entry.getName());
    }

    @Override
    public InputStream inStream() {
        try{
            return file.getInputStream(entry);
        }catch(IOException e){
            throw new RuntimeException("Unable to open zip file entry " + e.getMessage());
        }
    }

    @Override
    public boolean exists() {
        return (file != null && entry != null);
    }

}