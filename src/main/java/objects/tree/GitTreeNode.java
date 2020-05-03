package objects.tree;

public class GitTreeNode {
    private String path;
    private String sha;
    private String mode;

    GitTreeNode(String path, String sha, String mode) {
        this.mode = mode;
        this.sha = sha;
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public String getSha() {
        return this.sha;
    }

    public String getMode() {
        return this.mode;
    }
}
