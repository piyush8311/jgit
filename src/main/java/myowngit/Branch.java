package myowngit;

class Branch {
    private String name;
    private Commit commit;

    Branch(String name, Commit commit) {
        this.name = name;
        this.commit = commit;
    }

    void setCommit(Commit commit) {
        this.commit = commit;
    }

    Commit getCommit() {
        return this.commit;
    }

    String getName () {
        return this.name;
    }
}
