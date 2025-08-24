package one.devos.nautical.winterisms.utils

import me.saharnooby.qoi.QOIDecoder
import me.saharnooby.qoi.QOIUtil
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.chat.Component
import one.devos.nautical.winterisms.Winterisms
import org.apache.commons.lang3.StringUtils
import org.brotli.dec.BrotliInputStream
import java.io.File
import java.io.InputStream
import kotlin.io.encoding.Base64

fun convertHexStringAsAnInt(hexString: String): Int {
    return hexString.removePrefix("#").toInt(16)
}

fun fixXaerosString(text: String): String {
    return text.replaceBefore("xaero-waypoint:", "")
}

fun convertJourneyMapToXaeros(text: String): String {
    val raw = text.replace(Regex("[\\[\\]]"), "")

    val nameMatch = StringUtils.substringsBetween(text, "\"", "\"")
    var name = if (nameMatch != null && nameMatch.size > 0)
        nameMatch[0]
    else
        null

    var x: Int? = null
    var y = 63
    var z: Int? = null
    var dim = "minecraft:overworld"

    for (part in raw.split(",")) {
        if (part.contains(":")) {
            val prop = part.split(":")
            if (prop.size == 2 || (prop.size == 3 && part.contains("dim:"))) {
                val key = prop[0].trim().lowercase()
                val value = prop[1].trim()

                try {
                    when (key) {
                        "x" -> x = value.toInt()
                        "y" -> y = value.toInt()
                        "z" -> z = value.toInt()
                        "dim" -> dim = "$value:${prop[2].trim()}"
                        "name" -> {
                            if (name == null)
                                name = value.replace("\"", "")
                        }
                    }
                } catch (_: Throwable) {}
            }
        }
    }

    if (x == null || z == null)
        return text

    if (name == null)
        name = "$x,$z"

    return "xaero-waypoint:$name:W:$x:$y:$z:16:false:0:Internal-dim%${dim.replace(":", "$")}-waypoints"
}

// im fuckin *lazy*
// thanks Deftu for writing this generic for me when i asked about generics lol
inline fun <reified T> translate(key: String): T {
    return when (T::class) {
        String::class -> Component.translatable(key).string
        Component::class -> Component.translatable(key)
        else -> throw IllegalArgumentException("Unsupported type: ${T::class.simpleName}. Use String or Component.")
    } as T
}

// a shotgun is looking tasty right now
fun base64ToBrotliInputStream(base64String: String): BrotliInputStream {
    val decodedBase64 = Base64.decode(base64String)
    val brotliDecode = BrotliInputStream(decodedBase64.inputStream())

    return brotliDecode
}

fun brotliToByteArray(brotliInputStream: BrotliInputStream): ByteArray {
    return brotliInputStream.readBytes()
}

fun brotliDecodedByteArrayToQOIDecodableInputStream(brotliDecodedByteArray: ByteArray): InputStream {
    return brotliDecodedByteArray.inputStream()
}

// HOOOOOLY FUCKIN SHIT I DID IT I CANT FUCKING BELIEVE IT I CANT BELIEVE I DID IT I WROTE A REALLY SHITTY DECODER
fun altogetherNowTestFromBase64StringToFinalQOIImage(base64String: String) {
    val step1 = base64ToBrotliInputStream(base64String)
    val step2 = brotliToByteArray(step1)
    val step3 = QOIDecoder.decode(brotliDecodedByteArrayToQOIDecodableInputStream(step2), 4)
    val step4 = QOIUtil.writeImage(step3, File(FabricLoader.getInstance().gameDir.toFile(), "test.qoi"))
    val step5 = Winterisms.LOGGER.info("${QOIUtil.readImage(brotliDecodedByteArrayToQOIDecodableInputStream(step2)).width}, ${QOIUtil.readImage(brotliDecodedByteArrayToQOIDecodableInputStream(step2)).height}")
}