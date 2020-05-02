package myowngit;

import net.sourceforge.argparse4j.inf.Namespace;

public class MyOwnGit {
    public static void main(String[] args) throws Exception {

        Namespace namespace = CommandLineArgumentsParser.parse(args);
        String command = namespace.get("command");
        switch (command) {
            case "init": CommandFunctions.init(namespace);
            case "cat-file": CommandFunctions.catFile(namespace);
            case "hash-object": CommandFunctions.hashObject(namespace);
            default:
                System.out.println("Not a valid command");
        }
    }
}
