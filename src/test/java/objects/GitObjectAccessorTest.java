package objects;

import org.junit.Test;
import repository.Repository;
import utils.UnitTestsBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GitObjectAccessorTest extends UnitTestsBase {

    private static final String TEST_DATA = "testData";

    @Test
    public void testSanity() throws Exception {
        Repository repository = new Repository("./testDir", true);
        String hash = GitObjectAccessor.writeObject(new GitBlob(repository, TEST_DATA), true);
        GitObject gitObject = GitObjectAccessor.readObject(repository, hash);

       assertThat(gitObject.serialize(), is(TEST_DATA));
    }
}
