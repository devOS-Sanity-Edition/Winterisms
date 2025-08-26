package one.devos.nautical.winterisms.utils

import org.apache.commons.lang3.StringUtils

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