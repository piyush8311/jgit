package myowngit;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RepositoryTest {

    @Test
    public void testCommit() {
        Repository repository = new Repository("TestRepo");
        Commit testCommit = repository.commit("Initial commit");

        assertThat(repository.getHead(), is(testCommit));
        assertThat(testCommit.getId(), is(0));
    }

    @Test
    public void testLog() {
        Repository repository = new Repository("TestRepo");
        repository.commit("Test commit 1");
        repository.commit("Test commit 2");

        List<Commit> commits = repository.log();

        assertThat(commits.size(), is(2));
        assertThat(commits.get(0), is(repository.getHead()));
    }
}
