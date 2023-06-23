package cryptography

import java.awt.Color
import java.awt.image.BufferedImage

const val BYTE_SIZE = 8
class Steganograph {

    fun encode(image: BufferedImage, message: String): BufferedImage {

        val byteArray = message.encodeToByteArray().toMutableList()
        byteArray.addAll(listOf(0, 0, 3)) // End of message

        if (byteArray.size * BYTE_SIZE > image.width * image.height)
            throw RuntimeException("The input image is not large enough to hold this message.")

        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                if ((y * image.width + x) / BYTE_SIZE == byteArray.size)
                    return image

                val byte = byteArray[(y * image.width + x) / BYTE_SIZE].toInt()
                val bit = (y * image.width + x) % BYTE_SIZE

                val color = Color(image.getRGB(x, y))
                val r = color.red
                val g = color.green
                val b = color.blue shr(1) shl(1) or isSet(byte, bit) // reset the lowest bit to 0 and apply binary OR

                val colorNew = Color(r, g, b)
                image.setRGB(x, y, colorNew.rgb)
            }
        }
        return image
    }

    private fun isSet(byte: Int, bit: Int): Int {
        val mask = arrayOf(128, 64, 32, 16, 8, 4, 2, 1)
        return if (byte and mask[bit] == 0) 0 else 1
    }

    fun decode(inputImage: BufferedImage): String {
        val byteArray = emptyArray<Byte>().toMutableList()

        for (y in 0 until inputImage.height) {
            for (x in 0 until inputImage.width) {

                if ((y * inputImage.width + x) % BYTE_SIZE == 0) byteArray.add(0)
                val color = Color(inputImage.getRGB(x, y))

                byteArray[byteArray.lastIndex] =
                    (byteArray.last().toInt() or setBit(color.blue and 1, (y * inputImage.width + x) % BYTE_SIZE)).toByte()

                // Check if End of message (0, 0, 3)
                if (byteArray.last() == 3.toByte() &&
                    byteArray[byteArray.lastIndex - 1] == 0.toByte() &&
                    byteArray[byteArray.lastIndex - 2] == 0.toByte()) {
                    if (byteArray.size > 3 ) {
                        return byteArray.subList(0, byteArray.lastIndex - 2).toByteArray().toString(Charsets.UTF_8)
                    }
                }
            }
        }
        return byteArray.subList(0, byteArray.lastIndex - 2).toByteArray().toString(Charsets.UTF_8)
    }

    private fun setBit(set: Int, bit: Int): Int {
        val mask = arrayOf(128, 64, 32, 16, 8, 4, 2, 1)
        return if (set != 0) mask[bit] else 0
    }

}