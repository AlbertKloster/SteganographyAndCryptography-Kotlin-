package cryptography

fun main() {
    var exit = false

    while (!exit) {
        println("Task (hide, show, exit):")
        try {
            when (Commands.getCommand(readln())) {
                Commands.HIDE -> hide()
                Commands.SHOW -> show()
                Commands.EXIT -> exit = true
            }
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }
    println("Bye!")
}

private fun hide() {
    println("Hiding message in image.")
}

private fun show() {
    println("Obtaining message from image.")
}

