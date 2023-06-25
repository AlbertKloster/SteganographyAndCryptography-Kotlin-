package cryptography

val FH = FileHandler()
val CG = Steganograph()
val XC = XorCoder()

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
    println("Input image file:")
    val inputImageName = readln()
    println("Output image file:")
    val  outputImageName = readln()
    println("Message to hide:")
    val message = readln()
    println("Password:")
    val password = readln()
    val encodedMessage = XC.getXorString(message, password)

    try {
        val inputImage = FH.readImage(inputImageName)
        val outputImage = CG.encode(inputImage, encodedMessage)
        FH.saveImage(outputImage, outputImageName)
        println("Message saved in $outputImage image.")
    } catch (e: RuntimeException) {
        println(e.message)
    }

}

private fun show() {
    println("Input image file:")
    val inputImageName = readln()
    println("Password:")
    val password = readln()

    try {
        val inputImage = FH.readImage(inputImageName)
        val encodedMessage = CG.decode(inputImage)
        println("Message:")
        println(XC.getXorString(encodedMessage, password))
    } catch (e: RuntimeException) {
        println(e.message)
    }
}
