package objects.tree;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GitTreeSerializerTest {

    private static final String TEST_STRING = "mode1 path1\0sha1\n"
            + "mode2 path2\0sha2\n";
    private static final List<GitTreeNode> TEST_NODES = getTestNodes();

    private static List<GitTreeNode> getTestNodes() {
        List<GitTreeNode> gitTreeNodes = new ArrayList<>();
        gitTreeNodes.add(new GitTreeNode("path1", "sha1", "mode1"));
        gitTreeNodes.add(new GitTreeNode("path2", "sha2", "mode2"));

        return gitTreeNodes;
    }

    @Test
    public void testDeserialize() {
        List<GitTreeNode> nodes = GitTreeSerializer.deserialize(TEST_STRING);

        assertThat(nodes.size(), is(2));
        assertThat(nodes.get(0).getMode(), is("mode1"));
        assertThat(nodes.get(0).getPath(), is("path1"));
        assertThat(nodes.get(0).getSha(), is("sha1"));
        assertThat(nodes.get(1).getMode(), is("mode2"));
        assertThat(nodes.get(1).getPath(), is("path2"));
        assertThat(nodes.get(1).getSha(), is("sha2"));
    }

    @Test
    public void testSerialize() {
        assertThat(GitTreeSerializer.serialize(TEST_NODES), is(TEST_STRING));
    }

}
