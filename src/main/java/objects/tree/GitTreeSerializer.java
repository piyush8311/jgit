package objects.tree;

import java.util.ArrayList;
import java.util.List;

/*
    This is a very unoptimized implementation of tree storage.
 */
public class GitTreeSerializer {
    public static String serialize(List<GitTreeNode> gitTreeNodes) {
        StringBuilder result = new StringBuilder();

        gitTreeNodes.forEach(node -> result.append(node.getMode())
                                            .append(" ")
                                            .append(node.getPath())
                                            .append("\0")
                                            .append(node.getSha())
                                            .append("\n"));

        return result.toString();
    }

    public static List<GitTreeNode> deserialize(String data) {
        List<GitTreeNode> gitTreeNodes = new ArrayList<>();
        String[] treeNodeString = data.split("\n");
        for (String treeNode: treeNodeString) {
            int spaceCharIndex = treeNode.indexOf(' ');
            String mode = treeNode.substring(0, spaceCharIndex);

            int nullCharIndex = treeNode.indexOf('\0', spaceCharIndex);
            String path = treeNode.substring(spaceCharIndex + 1, nullCharIndex);
            String sha = treeNode.substring(nullCharIndex + 1);

            gitTreeNodes.add(new GitTreeNode(path, sha, mode));
        }

        return gitTreeNodes;
    }
}
