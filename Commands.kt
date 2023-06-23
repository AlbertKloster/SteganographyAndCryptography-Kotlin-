package cryptography

enum class Commands(val string: String) {
    HIDE("hide"), SHOW("show"), EXIT("exit");

    companion object {
        fun getCommand(input: String): Commands {
            for (command in Commands.values()) {
                if (command.string == input.lowercase()) {
                    return command
                }
            }
            throw RuntimeException("Wrong task: $input")
        }
    }
}