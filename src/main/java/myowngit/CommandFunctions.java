package myowngit;

import java.nio.file.Files;
import java.nio.file.Paths;
import net.sourceforge.argparse4j.inf.Namespace;
import objects.GitObject;
import objects.GitObjectAccessor;
import objects.GitObjectFactory;
import repository.Repository;

public class CommandFunctions {
    public static void init(Namespace namespace) throws Exception {
        String path = namespace.get("path");
        Repository repo = new Repository(path, true);
        repo.createRepository();
    }

    public static void catFile(Namespace namespace) throws Exception {
        Repository repository = Repository.findRepository("./");
        String hash = namespace.getString("hash");
        System.out.println(GitObjectAccessor.readObject(repository, hash).serialize());
    }

    public static void hashObject(Namespace namespace) throws Exception {
        Repository repository = Repository.findRepository("./");
        String path = namespace.getString("path");
        String type = namespace.getString("type");
        boolean shouldActuallyWrite = namespace.getBoolean("write");
        String data = new String(Files.readAllBytes(Paths.get(path)));
        GitObject gitObject = GitObjectFactory.ofType(type, repository, data);

        System.out.println(GitObjectAccessor.writeObject(gitObject, shouldActuallyWrite));
    }
}
