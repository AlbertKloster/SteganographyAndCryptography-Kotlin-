package cryptography

import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class FileHandler {

    fun readImage(filename: String): BufferedImage {
        val imageFile = File(filename)
        if (!imageFile.exists()) {
            throw IOException("Can't read input file!")
        }
        return ImageIO.read(imageFile)
    }

    fun saveImage(image: BufferedImage, filename: String) {
        ImageIO.write(image, "png", File(filename))
    }

}