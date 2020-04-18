package myowngit;

class Commit {
    // Using integers for simplicity's sake
    private int id;
    private String message;
    private Commit parentCommit;
    //TODO: Implement functionality which stores information about the change

    Commit(int id, String message, Commit parentCommit) {
        this.id = id;
        this.message = message;
        this.parentCommit = parentCommit;
    }

    public int getId() {
        return this.id;
    }

    public Commit getParentCommit() {
        return this.parentCommit;
    }
}
