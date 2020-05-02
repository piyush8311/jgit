package myowngit;

import net.sourceforge.argparse4j.inf.Namespace;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommandLineArgumentsParserTest {

    @Test
    public void test_init() {
        String[] args = {"init", "../testPath"};
        Namespace namespace = CommandLineArgumentsParser.parse(args);

        assertThat(namespace.get("command"), is("init"));
        assertThat(namespace.get("path"), is("../testPath"));
    }

    @Test
    public void test_hashObject() {
        String[] args = {"hash-object", "-t", "commit", "../testPath"};
        Namespace namespace = CommandLineArgumentsParser.parse(args);

        assertThat(namespace.get("command"), is("hash-object"));
        assertThat(namespace.get("path"), is("../testPath"));
        assertThat(namespace.get("type"), is("commit"));
        assertThat(namespace.get("write"), is(false));
    }

    @Test
    public void test_catFile() {
        String[] args = {"cat-file", "blob", "abc"};
        Namespace namespace = CommandLineArgumentsParser.parse(args);

        assertThat(namespace.get("command"), is("cat-file"));
        assertThat(namespace.get("type"), is("blob"));
        assertThat(namespace.get("object"), is("abc"));
    }
}
