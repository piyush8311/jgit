package objects;

import org.junit.Test;
import repository.Repository;
import utils.UnitTestsBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GitBlobTest extends UnitTestsBase {

    @Test
    public void testSanity() throws Exception {
        Repository repository = new Repository("./testDir", true);
        GitObject gitObject = new GitBlob(repository, "testData");
        assertThat(gitObject.serialize(), is("testData"));
        gitObject.deserialize("testData2");
        assertThat(gitObject.serialize(), is("testData2"));
    }
}
