package objects;

import repository.Repository;

public class GitObjectFactory {
    public static GitObject ofType(String type, Repository repository, String data) {
        GitObjectType gitObjectType = GitObjectType.valueOf(type);
        switch (gitObjectType) {
            case BLOB:
                return new GitBlob(repository, data);
            case COMMIT:
                return new GitCommit(repository, data);
            case TAG:
                return new GitTag(repository, data);
            case TREE:
                return new GitTree(repository, data);
            default:
                throw new RuntimeException("Invalid GitType :" + gitObjectType.name());
        }
    }
}
