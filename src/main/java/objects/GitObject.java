package objects;

import repository.Repository;

public class GitObject {
    Repository repository;
    String data;
    public GitObject(Repository repository, String data) {
        this.repository = repository;
        if (data != null) {
            this.deserialize(data);
        }
    }

    public String serialize() {
        throw new RuntimeException("No implementation available!");
    }

    public void deserialize(String data) {
        throw new RuntimeException("No implementation available!!");
    }

    public String getType() {
        throw new RuntimeException("No implementation available");
    }

    public Repository getRepository() {
        return this.repository;
    }
}
