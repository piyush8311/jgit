package myowngit;

public enum CommandName {
    INIT("init"),
    CATFILE("cat-file"),
    HASHOBJECT("hash-object");

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
