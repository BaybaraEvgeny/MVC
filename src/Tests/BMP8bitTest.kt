package model

import java.io.File
import java.io.FileInputStream
import javax.imageio.ImageIO.read
import java.awt.image.BufferedImage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


internal class BMP8bitTest {

    @Test
    fun createImage() {

        val files = listOf("bogts_8bit.bmp", "freebsd2_8bit.bmp")
        var model: BMP8bit
        var expectedImage: BufferedImage

        for (i in files) {

            print(i + ": ")

            val file = FileInputStream("C:/input/" + i)
            val data = ByteArray(file.available())
            file.read(data)

            expectedImage = read(File("C:/input/" + i))
            model = BMP8bit(i, data)

            model.parseHeader()
            model.createImage()

            assertEquals(expectedImage.height, model.image.height)
            print("height-ok, ")
            assertEquals(expectedImage.width, model.image.width)
            print("width-ok, ")

            for (j in 0..(model.image.height - 1)) {
                for (k in 0..(model.image.width - 1)) {

                    val actualPixel = model.image.getRGB(k, j)
                    val expectedPixel = expectedImage.getRGB(k, j)

                    assertEquals(expectedPixel, actualPixel)

                }
            }

            println("image-ok.")

        }

    }

}