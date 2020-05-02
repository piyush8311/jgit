package objects;

import repository.Repository;

public class GitBlob extends GitObject {
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
}
