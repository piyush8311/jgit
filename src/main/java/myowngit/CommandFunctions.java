package myowngit;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.sourceforge.argparse4j.inf.Namespace;
import objects.GitObject;
import objects.GitObjectAccessor;
import objects.GitObjectFactory;
import objects.GitObjectType;
import objects.blob.GitBlob;
import objects.commit.GitCommit;
import objects.tree.GitTree;
import objects.tree.GitTreeNode;
import repository.Repository;

public class CommandFunctions {
    /*
     * git init [path] (optional)
     */
    public static void init(Namespace namespace) throws Exception {
        String path = namespace.get("path");
        Repository repo = new Repository(path, true);
        repo.createRepository();
    }

    /*
     * git cat-file [type] [hash]
     */
    public static void catFile(Namespace namespace) throws Exception {
        Repository repository = Repository.findRepository("./");
        String hash = namespace.getString("object");
        System.out.println(GitObjectAccessor.readObject(repository, hash).serialize());
    }

    /*
     * git hash-object [path] [-t type] (optional) -w (optional)
     */
    public static void hashObject(Namespace namespace) throws Exception {
        Repository repository = Repository.findRepository("./");
        String path = namespace.getString("path");
        String type = namespace.getString("type");
        boolean shouldActuallyWrite = namespace.getBoolean("write");
        String data = new String(Files.readAllBytes(Paths.get(path)));
        GitObject gitObject = GitObjectFactory.ofType(type, repository, data);

        System.out.println(GitObjectAccessor.writeObject(gitObject, shouldActuallyWrite));
    }

    /*
     * git ls-tree [hash]
     */
    public static void lsTree(Namespace namespace) throws Exception {
        Repository repository = Repository.findRepository("./");
        GitTree gitTree = (GitTree) GitObjectAccessor.readObject(repository, namespace.getString("object"));

        for (GitTreeNode node: gitTree.getTreeNodes()) {
            System.out.println(String.format("%s %s %s %s",
                    node.getMode(),
                    GitObjectAccessor.readObject(repository, node.getSha()).getType(),
                    node.getSha(), node.getPath()));
        }
    }

    /*
     * git commit -m [message]
     */
    public static void commit(Namespace namespace) throws Exception {
        String message = namespace.get("message");
        System.out.println(message);
    }

    /*
     * git checkout [commitHash] [path]
     */
    public static void checkout(Namespace namespace) throws Exception {
        Repository repository = Repository.findRepository(".");
        String hash = namespace.get("commit");
        GitObject gitObject = GitObjectAccessor.readObject(repository, hash);

        if (GitObjectType.valueOf(gitObject.getType()).equals(GitObjectType.commit)) {
            GitCommit gitCommit = (GitCommit) gitObject;
            gitObject = GitObjectAccessor.readObject(repository, gitCommit.getKvlm().get("tree").get(0));
        }

        String path = namespace.getString("path");
        File file = new File(path);
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new RuntimeException("Not a directory: " + path);
            }
            if (file.listFiles() != null && file.listFiles().length != 0) {
                throw new RuntimeException("Not empty directory " + path);
            }
        } else {
            file.mkdirs();
        }

        treeCheckout(repository, (GitTree) gitObject, path);
    }

    private static void treeCheckout(Repository repository, GitTree gitTree, String path) throws Exception{
        for (GitTreeNode node: gitTree.getTreeNodes()) {
            GitObject gitObject = GitObjectAccessor.readObject(repository, node.getSha());
            String dest = Paths.get(path, node.getPath()).toString();
            File file = new File(dest);
            GitObjectType gitObjectType = GitObjectType.valueOf(gitObject.getType());
            if (GitObjectType.tree.equals(gitObjectType)) {
                file.mkdir();
                treeCheckout(repository, (GitTree) gitObject, dest);
            } else if (GitObjectType.blob.equals(gitObjectType)) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(((GitBlob) gitObject).getData());
            }
        }
    }
}
