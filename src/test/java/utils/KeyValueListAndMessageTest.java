package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class KeyValueListAndMessageTest {
    private static final String TEST_STRING = "tree 29ff16c9c14e2652b22f8b78bb08a5a07930c147\n"
            + "tree 29ff16c9c14e2652b22f8b78bb08a5a07930c1s7\n"
            + "committer Thibault Polge <thibault@thb.lt> 1527025044 +0200\n"
            + "gpgsig -----BEGIN PGP SIGNATURE-----\n"
            + " iQIzBAABCAAdFiEExwXquOM8bWb4Q2zVGxM2FxoLkGQFAlsEjZQACgkQGxM2FxoL\n"
            + " -----END PGP SIGNATURE-----\n"
            + "\n"
            + "Create first draft";

    private static final Map<String, List<String>> KVLM = getKvlmMap();

    private static Map<String, List<String>> getKvlmMap() {
        Map<String, List<String>> kvlm = new LinkedHashMap<>();
        kvlm.put("tree", new ArrayList<>(Arrays.asList("29ff16c9c14e2652b22f8b78bb08a5a07930c147", "29ff16c9c14e2652b22f8b78bb08a5a07930c1s7")));
        kvlm.put("committer", new ArrayList<>(
                Collections.singletonList("Thibault Polge <thibault@thb.lt> 1527025044 +0200")));
        kvlm.put(
                "gpgsig",
                new ArrayList<>(
                        Collections.singletonList(
                                "-----BEGIN PGP SIGNATURE-----\niQIzBAABCAAdFiEExwXquOM8bWb4Q2zVGxM2FxoLkGQFAlsEjZQACgkQGxM2FxoL\n-----END PGP SIGNATURE-----")));
        kvlm.put("", new ArrayList<>(Collections.singletonList("Create first draft")));

        return kvlm;
    }

    @Test
    public void testSerialize() {
        assertThat(KeyValueListAndMessage.serialize(KVLM), is(TEST_STRING));
    }

    @Test
    public void testParse() {
        assertThat(KeyValueListAndMessage.parse(TEST_STRING), is(KVLM));
    }
}
