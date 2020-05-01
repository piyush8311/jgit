import java.io.File;
import org.junit.After;
import org.junit.Before;

public class UnitTestsBase {
    @Before
    public void setup() {
        File file = new File("./testDir");
        file.mkdir();
    }

    @After
    public void tearDown() {
        File file = new File("./testDir");
        deleteDirectory(file);
    }

    private void deleteDirectory(File file) {
        File[] allContents = file.listFiles();
        if (allContents != null) {
            for (File f: allContents) {
                deleteDirectory(f);
            }
        }
        file.delete();
    }
}
