package objects;

import java.util.Map;
import repository.Repository;
import utils.KeyValueListAndMessage;

public class GitCommit extends GitObject {
    //Key-Value list and message
    private  Map<String, String> kvlm;

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
}
