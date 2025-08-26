package one.devos.nautical.winterisms.utils

import com.sun.jna.platform.win32.Kernel32
import com.sun.jna.platform.win32.WinNT
import net.fabricmc.loader.api.FabricLoader
import one.devos.nautical.winterisms.Winterisms

fun modLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)

fun pairModsLoaded(modId1: String, modId2: String) =
    FabricLoader.getInstance().isModLoaded(modId1) && FabricLoader.getInstance().isModLoaded(modId2)

fun isWindows11(): Boolean {
    val osVersionInfo = WinNT.OSVERSIONINFO()
    Kernel32.INSTANCE.GetVersionEx(osVersionInfo)

    if (osVersionInfo.dwBuildNumber.toLong() < 22000L) {
        Winterisms.LOGGER.info("[Winterisms] Not running Windows 11, will not attempt to apply dark mode titlebar.")
        return false
    }

    Winterisms.LOGGER.info("[Winterisms] Running Windows 11, will attempt to apply dark mode titlebar.")
    return true
}