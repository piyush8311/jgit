package myowngit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a repository and all the branches it has.
 */
public class Repository {
    private static final String DEFAULT_BRANCH_NAME = "master";

    private int lastCommitId;
    private String name;
    private Branch HEAD;
    private Map<String, Branch> allBranches;

    public Repository(String name) {
        this.name = name;
        this.lastCommitId = -1;
        this.HEAD = new Branch(DEFAULT_BRANCH_NAME, null);
        allBranches = new HashMap<>();
        allBranches.put(DEFAULT_BRANCH_NAME, this.HEAD);
    }

    public Branch getHead() {
        return this.HEAD;
    }

    /**
     *  Creates a new commit with the message passed.
     */
     public Commit commit(String message) {
        Commit commit = new Commit(++this.lastCommitId, message, HEAD.getCommit());
        this.HEAD.setCommit(commit);
        return commit;
    }

    /**
     *  Returns all the commits in the current branch
     */
    public List<Commit> log() {
        List<Commit> log = new ArrayList<>();

        Commit commit = HEAD.getCommit();
        while (commit != null) {
            log.add(commit);
            commit = commit.getParentCommit();
        }

        return log;
    }

    /**
     * Used to change current branch to branchName. Creates a new branch with
     * name branchName if it doesn't already exist
     */
    public void checkout(String branchName) {
        if (allBranches.containsKey(branchName)) {
            System.out.println("Switching to branch " + branchName);
        } else {
            System.out.println("Branch " + branchName + " does not exist! Creating a new branch");
            this.allBranches.put(branchName, new Branch(branchName, HEAD.getCommit()));
        }

        this.HEAD = allBranches.get(branchName);
    }
}
