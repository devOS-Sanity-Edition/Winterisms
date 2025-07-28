package one.devos.nautical.winterssummerfixes.utils

fun convertHexStringAsAnInt(hexString: String): Int {
    return hexString.removePrefix("#").toInt(16)
}