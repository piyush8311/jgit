package objects.tree;

import java.util.List;
import objects.GitObject;
import objects.GitObjectType;
import repository.Repository;

public class GitTree extends GitObject {
    private List<GitTreeNode> treeNodes;

    public GitTree(Repository repository, String data) {
        super(repository, data);
    }

    @Override
    public String serialize() {
        return GitTreeSerializer.serialize(treeNodes);
    }

    @Override
    public void deserialize(String data) {
        this.treeNodes = GitTreeSerializer.deserialize(data);
    }

    @Override
    public String getType() {
        return GitObjectType.tree.name();
    }

    public List<GitTreeNode> getTreeNodes() {
        return this.treeNodes;
    }
}
