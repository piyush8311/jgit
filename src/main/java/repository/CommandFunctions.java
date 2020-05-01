package repository;

public class CommandFunctions {
    public static void init(String path) throws Exception {
        Repository repo = new Repository(path, true);
        repo.createRepository();
    }

    public static void commit() {

    }
}
