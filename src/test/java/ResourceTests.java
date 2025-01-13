import jpize.util.array.StringList;
import jpize.util.res.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

public class ResourceTests {

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
        Assert.assertEquals(new StringList("128", "256"), res.readLines());
    }

    @Test
    public void internalFileTest2() {
        final InternalResource res = Resource.internal("/testdir/testfile2.txt");
        Assert.assertTrue(res.exists());
        Assert.assertEquals(new StringList("64", "16", "2"), res.readLines());
    }

    @Test
    public void urlTest1() {
        final URLResource res = Resource.url("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
        Assert.assertTrue(res.exists());
        Assert.assertEquals(-250076188, res.readString().hashCode());
    }

    @Test
    public void zipTest1() throws IOException {
        final ZipFile file = new ZipFile("./src/test/resources/test.zip");
        final ZipResource[] resources = Resource.zip(file);
        Assert.assertEquals(4, resources.length);
        Assert.assertTrue(resources[0].isDir());
        Assert.assertTrue(resources[1].isFile());
        Assert.assertEquals("blocks", resources[0].name());
        Assert.assertEquals("dirt.json", resources[1].name());
    }

}
