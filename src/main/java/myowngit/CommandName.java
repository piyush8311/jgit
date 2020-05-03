package myowngit;

public enum CommandName {
    CATFILE("cat-file"),
    CHECKOUT("checkout"),
    COMMIT("commit"),
    HASHOBJECT("hash-object"),
    INIT("init"),
    LSTREE("ls-tree");

    String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public static CommandName valueOfCommandName(String commandName) {
        for (CommandName c: values()) {
            if (c.commandName.equals(commandName)) {
                return c;
            }
        }
        return null;
    }
}
