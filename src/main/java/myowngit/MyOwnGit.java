package myowngit;

import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.ArgumentParsers;

public class MyOwnGit {
    public static void main(String[] args) {
        ArgumentParser argumentParser = ArgumentParsers.newFor("MyOwnGit").build();

        try {
            argumentParser.parseArgs(args);
        } catch (Exception e) {
            System.out.println("Error while parsing args!!");
            return;
        }

        System.out.println("Test123");
    }
}
