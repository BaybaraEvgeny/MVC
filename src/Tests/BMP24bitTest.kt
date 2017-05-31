import model.*

import java.io.File
import java.io.FileInputStream
import javax.imageio.ImageIO.read
import java.awt.image.BufferedImage
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


internal class BMP24bitTest {

    @Test
    fun createImage() {

        val files = listOf("beaut_24bit.bmp", "dodj_24bit.bmp",  "su85_24bit.bmp")
        var model: BMP24bit
        var expectedImage: BufferedImage

        for (i in files) {

            print(i + ": ")

            val file = FileInputStream("C:/input/" + i)
            val data = ByteArray(file.available())
            file.read(data)

            expectedImage = read(File("C:/input/" + i))
            model = BMP24bit(i, data)

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