package myowngit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Used to create a new commit in a repo
    public Commit commit(String message) {
        Commit commit = new Commit(++this.lastCommitId, message, HEAD.getCommit());
        this.HEAD.setCommit(commit);
        return commit;
    }

    // Used to log all the commits in the repo
    public List<Commit> log() {
        List<Commit> log = new ArrayList<>();

        Commit commit = HEAD.getCommit();
        while (commit != null) {
            log.add(commit);
            commit = commit.getParentCommit();
        }

        return log;
    }

    // Used to change current branch
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
