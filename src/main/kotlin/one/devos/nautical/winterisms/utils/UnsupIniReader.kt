package one.devos.nautical.winterisms.utils

import net.fabricmc.loader.api.FabricLoader
import org.ini4j.Wini
import java.io.File
import java.io.IOException

object UnsupIniReader {
    val unsupIniFile = File(FabricLoader.getInstance().gameDir.toFile(), "unsup.ini")
    val unsupIni = Wini(unsupIniFile)

    @get:Throws(IOException::class)
    val brandingTitle: String = unsupIni.get("branding", "modpack_name")

    @get:Throws(IOException::class)
    val brandingQOIBase64String: String = unsupIni.get("branding", "icon")
}