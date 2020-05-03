package objects.commit;

import java.util.List;
import java.util.Map;
import objects.GitObject;
import objects.GitObjectType;
import repository.Repository;

public class GitCommit extends GitObject {
    //Key-Value list and message
    private  Map<String, List<String>> kvlm;

    public GitCommit(Repository repository, String data) {
        super(repository, data);
    }

    @Override
    public String serialize() {
        return KeyValueListAndMessage.serialize(kvlm);
    }

    @Override
    public void deserialize(String data) {
        this.kvlm = KeyValueListAndMessage.parse(data);
    }

    @Override
    public String getType() {
        return GitObjectType.commit.name();
    }

    public Map<String, List<String>> getKvlm() {
        return this.kvlm;
    }
}
