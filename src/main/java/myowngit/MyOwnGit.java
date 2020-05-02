package myowngit;

import net.sourceforge.argparse4j.inf.Namespace;

public class MyOwnGit {
    public static void main(String[] args) throws Exception {
        Namespace namespace = CommandLineArgumentsParser.parse(args);
        CommandName command = CommandName.valueOfCommandName(namespace.get("command"));

        switch (command) {
            case INIT:
                CommandFunctions.init(namespace);
                break;
            case CATFILE:
                CommandFunctions.catFile(namespace);
                break;
            case HASHOBJECT:
                CommandFunctions.hashObject(namespace);
                break;
            default:
                System.out.println("Not a valid command");
        }
    }
}