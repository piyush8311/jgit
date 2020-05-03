package objects.tree;

class GitTreeNode {
    private String path;
    private String sha;
    private String mode;

    GitTreeNode(String path, String sha, String mode) {
        this.mode = mode;
        this.sha = sha;
        this.path = path;
    }

    String getPath() {
        return this.path;
    }

    String getSha() {
        return this.sha;
    }

    String getMode() {
        return this.mode;
    }
}
