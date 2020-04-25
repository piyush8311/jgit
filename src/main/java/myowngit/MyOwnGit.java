package myowngit;

import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.ArgumentParsers;

public class MyOwnGit {
    public static void main(String[] args) throws Exception {

        String commandArgument = args[0];

        switch (commandArgument) {
            case "init": CommandFunctions.init("./");
            case "commit": CommandFunctions.commit();
            default:
                System.out.println("Not a valid command");
        }
    }
}
