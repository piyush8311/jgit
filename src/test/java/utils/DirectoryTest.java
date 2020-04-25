package utils;

import org.junit.Test;

import java.io.File;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DirectoryTest {
    private final Directory directory = new Directory("./");

    @Test
    public void test_JoinWithRepoPath_SinglePath() {
        String path = directory.joinWithRepoPath("testPath");
        assertThat(path, is("./testPath"));
    }

    @Test
    public void test_JoinWithRepoPath_MultiplePath() {
        String path = directory.joinWithRepoPath("testPath1", "testPath2");
        assertThat(path, is("./testPath1/testPath2"));
    }

    @Test
    public void test_JoinWithRepoPath_NoPath() {
        String path = directory.joinWithRepoPath();
        assertThat(path, is("."));
    }

    @Test
    public void test_JoinFileWithRepoPath_SinglePath() {
        Optional<String> path = directory.joinFileWithRepoPath(false, "testFile");
        assertThat(path.isEmpty(), is(false));
        assertThat(path.get(), is("./testFile"));
    }

    @Test
    public void test_JoinFileWithRepoPath_MultiplePath() {
        Optional<String> path = directory.joinFileWithRepoPath(true, "testDir", "testFile");
        assertThat(path.isEmpty(), is(false));
        assertThat(path.get(), is("./testDir/testFile"));
        File file = new File("./testDir");
        file.delete();
    }

    @Test
    public void test_JoinDirWithRepoPath_singlePath() {
        Optional<String> path = directory.joinDirWithRepoPath(true, "testDir");
        assertThat(path.isEmpty(), is(false));
        assertThat(path.get(), is("./testDir"));

        assertDirectory("./testDir");
    }

    @Test
    public void test_JoinDirWithRepoPath_multiplePath() {
        Optional<String> path = directory.joinDirWithRepoPath(true, "testDir", "testDir1");
        assertThat(path.isEmpty(), is(false));
        assertThat(path.get(), is("./testDir/testDir1"));

        assertDirectory("./testDir/testDir1");
        assertDirectory("./testDir");
    }

    private void assertDirectory(String path) {
        File file = new File(path);
        assertThat(file.exists(), is(true));
        assertThat(file.isDirectory(), is(true));
        file.delete();
    }
}
