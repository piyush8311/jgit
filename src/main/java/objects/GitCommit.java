package objects;

import repository.Repository;

public class GitCommit extends GitObject {
    public GitCommit(Repository repository, String data) {
        super(repository, data);
    }
}
