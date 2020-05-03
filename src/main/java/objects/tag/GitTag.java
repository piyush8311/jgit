package objects.tag;

import objects.GitObject;
import repository.Repository;

public class GitTag extends GitObject {
    public GitTag(Repository repository, String data) {
        super(repository, data);
    }
}
