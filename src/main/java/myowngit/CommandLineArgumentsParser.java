package myowngit;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.action.StoreTrueArgumentAction;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import net.sourceforge.argparse4j.inf.Subparsers;

public class CommandLineArgumentsParser {
    private static Subparsers subparsers;

    public static Namespace parse(String[] args)  {

        ArgumentParser parser = ArgumentParsers.newFor("myowngit").build()
                .description("process my own git commands");
        subparsers = parser.addSubparsers().title("Commands").dest("command");

        addInitSubparser();
        addHashObjectSubParser();

        try{
            return parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            throw new RuntimeException("Error while parsing command line args " + args, e);
        }
    }

    private static void addInitSubparser() {
        Subparser initParser = subparsers.addParser("init").help("init help");
        initParser.addArgument("path")
                .metavar("directory")
                .nargs("?")
                .setDefault(".").help("Where to create the repository");

    }

    private static void addHashObjectSubParser() {
        Subparser hashObjectParser = subparsers.addParser("hash-object").help("init help");
        hashObjectParser.addArgument("-t")
                .metavar("type")
                .dest("type")
                .choices("blob", "commit", "tag", "tree")
                .setDefault("blob")
                .help("Specify the type");

        hashObjectParser.addArgument("-w")
                .dest("write")
                .action(new StoreTrueArgumentAction())
                .help("Actually write the object into the database");

        hashObjectParser.addArgument("path").help("Read object from <file>");
    }
}
