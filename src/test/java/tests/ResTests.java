package tests;

import jpize.util.array.StringList;
import jpize.util.res.ExternalResource;
import jpize.util.res.InternalResource;
import jpize.util.res.Resource;
import jpize.util.res.UrlResource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ResTests {

    private File folder;
    private File file;

    @Before
    public void createTmpFiles() {
        final String tmpdir = System.getProperty("java.io.tmpdir");
        try{
            folder = new File(tmpdir);
            file = File.createTempFile("jpize-res-test-", ".txt");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @After
    public void deleteTmpFiles() {
        file.delete();
    }


    @Test
    public void externalDirTest1() {
        final ExternalResource res = Resource.external(folder);
        Assert.assertTrue(res.exists());
        Assert.assertTrue(res.isDir());
    }

    @Test
    public void externalFileTest1() {
        final ExternalResource res = Resource.external(file);
        Assert.assertTrue(res.exists());
        Assert.assertTrue(res.isFile());
    }

    @Test
    public void internalDirTest1() {
        final InternalResource res = Resource.internal("/testdir");
        Assert.assertTrue(res.exists());
    }

    @Test
    public void internalFileTest1() {
        final InternalResource res = Resource.internal("/testfile1.txt");
        Assert.assertTrue(res.exists());
        Assert.assertEquals(res.readLines(), new StringList("line1", "line2", "line3"));
    }

    @Test
    public void internalFileTest2() {
        final InternalResource res = Resource.internal("/testdir/testfile2.txt");
        Assert.assertTrue(res.exists());
        Assert.assertEquals(res.readLines(), new StringList("line1", "line2", "line3", "line4"));
    }

    @Test
    public void urlTest1() {
        final UrlResource res = Resource.url("https://filesampleshub.com/download/document/txt/sample1.txt");
        Assert.assertTrue(res.exists());
        Assert.assertEquals(res.readString().hashCode(), -242807520);
    }

}
