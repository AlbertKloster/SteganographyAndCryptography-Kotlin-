package cryptography

import java.io.IOException

val FH = FileHandler()
val CG = Cryptograph()

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

    try {
        val inputImage = FH.readImage(inputImageName)
        val outputImage = CG.setLeastSignificantBitToOne(inputImage)
        FH.saveImage(outputImage, outputImageName)
        println("Input Image: $inputImageName")
        println("Output Image: $outputImageName")
        println("Image $outputImageName is saved.")
    } catch (e: IOException) {
        println(e.message)
    }

}

private fun show() {
    println("Obtaining message from image.")
}
