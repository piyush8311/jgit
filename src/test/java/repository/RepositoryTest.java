package myowngit;

import org.ini4j.Wini;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RepositoryTest {

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

    @Test
    public void testRepository_createRepo() throws Exception {
        Repository repository = new Repository("./testDir", true);

        assertThat(repository.gitdir, is("./testDir/.mygit"));
        assertThat(repository.repositoryDirectory, is(notNullValue()));
        assertThat(repository.configReader, is(nullValue()));

        repository.createRepository();

        assertThat(repository.configReader, is(notNullValue()));
        assertDirs();
        assertFiles();
        Repository repository1 = new Repository("./testDir", false);
        assertThat(repository1.configReader, is(notNullValue()));
    }

    private void assertDirs() {
        assertThat(new File("./testDir/.mygit/branches").isDirectory(), is(true));
        assertThat(new File("./testDir/.mygit/objects").isDirectory(), is(true));
        assertThat(new File("./testDir/.mygit/refs").isDirectory(), is(true));
        assertThat(new File("./testDir/.mygit/refs/tags").isDirectory(), is(true));
        assertThat(new File("./testDir/.mygit/refs/heads").isDirectory(), is(true));
    }

    private void assertFiles() {
        assertThat(new File("./testDir/.mygit/description").isFile(), is(true));
        assertThat(new File("./testDir/.mygit/HEAD").isFile(), is(true));
        File configFile = new File("./testDir/.mygit/config");
        assertThat(configFile.isFile(), is(true));

        try {
            Wini wini = new Wini(configFile);
            assertThat(wini.get("core", "repositoryformatversion", int.class), is(0));
            assertThat(wini.get("core", "filemode", boolean.class), is(false));
            assertThat(wini.get("core", "bare", boolean.class), is(false));
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Test
    public void test_findRepo() throws Exception {
        Repository repository = new Repository("./testDir", true);
        repository.createRepository();
        Repository repository1 = Repository.findRepository("./testDir/.mygit");
        String currentPath = new File(".").getAbsolutePath();
        assertThat(Paths.get(currentPath).relativize(Paths.get(repository1.gitdir)).toString(), is("testDir/.mygit"));
    }

    @Test(expected = FileNotFoundException.class)
    public void test_findRepo_noRepoFound() throws Exception {
        Repository repository1 = Repository.findRepository("./testDir/.mygit");
    }
}
