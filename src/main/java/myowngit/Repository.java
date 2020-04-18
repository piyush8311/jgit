package myowngit;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private int lastCommitId;
    private String name;
    private Commit HEAD;

    public Repository(String name) {
        this.name = name;
        this.lastCommitId = -1;
    }

    public Commit getHead() {
        return this.HEAD;
    }

    // Used to create a new commit in a repo
    public Commit commit(String message) {
        Commit commit = new Commit(++this.lastCommitId, message, HEAD);
        this.HEAD = commit;
        return commit;
    }

    // Used to log all the commits in the repo
    public List<Commit> log() {
        List<Commit> log = new ArrayList<>();

        Commit commit = HEAD;
        while (commit != null) {
            log.add(commit);
            commit = commit.getParentCommit();
        }

        return log;
    }
}
