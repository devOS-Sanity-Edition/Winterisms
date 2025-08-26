package one.devos.nautical.winterisms.utils

import net.fabricmc.loader.api.FabricLoader
import org.ini4j.Wini
import java.io.File
import java.io.IOException

object UnsupIni {
    val unsupIniFile = File(FabricLoader.getInstance().gameDir.toFile(), "unsup.ini")
    val unsupIni = Wini(unsupIniFile)

    @get:Throws(IOException::class)
    val brandingTitle: String get() = unsupIni.get("branding", "modpack_name")

    @get:Throws(IOException::class)
    val brandingQOIBase64String: String get() = unsupIni.get("branding", "icon")

    // unused
    fun setBrandingTitle(brandingTitle: String) {
        unsupIni.put("branding", "modpack_name", brandingTitle)
        unsupIni.store()
    }

    // unused
    fun setQOIBase64String(brandingQOIBase64String: String) {
        unsupIni.put("branding", "icon", brandingQOIBase64String)
        unsupIni.store()
    }
}