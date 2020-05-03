package objects.blob;

import objects.GitObject;
import objects.GitObjectType;
import repository.Repository;

public class GitBlob extends GitObject {
    private String data;
    public GitBlob(Repository repository, String data) {
        super(repository, data);
    }

    @Override
    public String serialize() {
        return this.data;
    }

    @Override
    public void deserialize(String data) {
        this.data = data;
    }

    @Override
    public String getType() {
        return GitObjectType.blob.name();
    }

    public String getData() {
        return this.data;
    }
}
