package one.devos.nautical.winterisms.client

import kotlinx.io.IOException
import net.fabricmc.loader.api.FabricLoader
import org.ini4j.Wini
import java.io.File
import kotlin.jvm.Throws

object UnsupIniReader {
    val unsupIniFile = File(FabricLoader.getInstance().gameDir.toFile(), "unsup.ini")
    val unsupIni = Wini(unsupIniFile)

    @get:Throws(java.io.IOException::class)
    val brandingTitle: String = unsupIni.get("branding", "modpack_name")

    @get:Throws(java.io.IOException::class)
    val brandingQOI: String = unsupIni.get("branding", "icon")
}