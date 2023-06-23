package cryptography

import java.awt.Color
import java.awt.image.BufferedImage

class Cryptograph {

    fun setLeastSignificantBitToOne(inputImage: BufferedImage): BufferedImage {
        val outputImage = BufferedImage(inputImage.width, inputImage.height, BufferedImage.TYPE_INT_RGB)
        for (x in 0 until inputImage.width) {
            for (y in 0 until inputImage.height) {
                val color = Color(inputImage.getRGB(x, y))
                outputImage.setRGB(x, y, Color(color.red or 1, color.green or 1, color.blue or 1).rgb)
            }
        }
        return outputImage
    }

}