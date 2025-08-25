package one.devos.nautical.winterisms.client.titlebar

import me.saharnooby.qoi.QOIDecoder
import me.saharnooby.qoi.QOIImage
import me.saharnooby.qoi.QOIUtil
import net.minecraft.client.Minecraft
import one.devos.nautical.winterisms.Winterisms
import one.devos.nautical.winterisms.config.Config
import one.devos.nautical.winterisms.utils.base64ToBrotliInputStream
import one.devos.nautical.winterisms.utils.brotliDecodedByteArrayToQOIDecodableInputStream
import one.devos.nautical.winterisms.utils.brotliToByteArray
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWImage
import org.lwjgl.stb.STBImage
import org.lwjgl.system.MemoryStack
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

object QOIWindowIcon {
    fun QOIImageToByteArray(qoiImage: QOIImage): ByteArray { // QOI
        var outputStream = ByteArrayOutputStream()

        QOIUtil.writeImage(qoiImage, outputStream)

        return outputStream.toByteArray()
    }

    fun loadIconBytes(byteArray: ByteArray): ByteBuffer? {
        val buffer = ByteBuffer.allocateDirect(byteArray.size).put(byteArray).flip()
        val icon = STBImage.stbi_load_from_memory(buffer, intArrayOf(32), intArrayOf(32), intArrayOf(4), 4)

        return icon
    }

    fun setWindowIcon(windowHandle: Long, qoiImageByteArrayOutput: ByteArray) {
        var icon: ByteBuffer? = ByteBuffer.allocate(0)

        try {
            MemoryStack.stackPush().use { stack ->
                icon = loadIconBytes(qoiImageByteArrayOutput)

                if (icon == null) { return }

                GLFWImage.malloc(1).use { icons ->
                    val iconImage = icons.get(0)
                    iconImage.set(32, 32, icon!!)
                    GLFW.glfwSetWindowIcon(windowHandle, icons)
                }
            }
        } catch (e: Exception) {
            Winterisms.LOGGER.error("Failed to set window icon from QOI Image Data: $e")
        } finally {
            if (icon != null) {
                STBImage.stbi_image_free(icon)
            }
        }
    }

    fun setIcon(byteArray: ByteArray) {
        val gameWindowHandle: Long = Minecraft.getInstance().window.window
        setWindowIcon(gameWindowHandle, byteArray)
    }

    fun okayLetsDoThisShit() {
        val step1 = Config.modpackQOIBase64Data
        val step2 = base64ToBrotliInputStream(step1)
        val step3 = brotliToByteArray(step2)
        val step4 = QOIDecoder.decode(brotliDecodedByteArrayToQOIDecodableInputStream(step3), 4)
        val step5 = QOIImageToByteArray(step4)
        val step6 = setIcon(step5)
    }
}