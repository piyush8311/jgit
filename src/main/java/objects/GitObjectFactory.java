package objects;

import repository.Repository;

public class GitObjectFactory {
    public static GitObject ofType(String type, Repository repository, String data) {
        GitObjectType gitObjectType = GitObjectType.valueOf(type);
        switch (gitObjectType) {
            case blob:
                return new GitBlob(repository, data);
            case commit:
                return new GitCommit(repository, data);
            case tag:
                return new GitTag(repository, data);
            case tree:
                return new GitTree(repository, data);
            default:
                throw new RuntimeException("Invalid GitType :" + gitObjectType.name());
        }
    }
}
