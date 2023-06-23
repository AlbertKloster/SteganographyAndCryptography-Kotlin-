package cryptography

val FH = FileHandler()
val CG = Steganograph()

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

    try {
        val inputImage = FH.readImage(inputImageName)
        val outputImage = CG.encode(inputImage, message)
        FH.saveImage(outputImage, outputImageName)
        println("Message saved in $outputImage image.")
    } catch (e: RuntimeException) {
        println(e.message)
    }

}

private fun show() {
    println("Input image file:")
    val inputImageName = readln()

    try {
        val inputImage = FH.readImage(inputImageName)
        val message = CG.decode(inputImage)
        println("Message:")
        println(message)
    } catch (e: RuntimeException) {
        println(e.message)
    }
}
