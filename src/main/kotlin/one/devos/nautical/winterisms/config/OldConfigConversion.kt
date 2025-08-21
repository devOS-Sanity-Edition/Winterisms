package one.devos.nautical.winterisms.config

import com.google.gson.JsonParser
import net.fabricmc.loader.api.FabricLoader
import one.devos.nautical.winterisms.Winterisms
import one.devos.nautical.winterisms.utils.convertHexStringAsAnInt
import java.awt.Color
import java.io.File

fun oldToNewConfigConversation() {
    val oldConfigFile = File(FabricLoader.getInstance().configDir.toFile(), "winterisms.json")
    if (oldConfigFile.exists()) {
        Winterisms.LOGGER.info("[Winterisms] Pre-2.1.0 Winterisms config detected! Lets write it to a compatible format!")
        val oldConfig = JsonParser.parseString(oldConfigFile.readText()).asJsonObject
        val oldConfigHitColor = oldConfig.get("limitsGrappleHitColor").asString
        val oldConfigMissColor = oldConfig.get("limitsGrappleMissColor").asString
        val oldConfigFlashbackExtreme = oldConfig.get("flashbackReplayForceAllowIncompatibleMods").asString
        val oldConfigWarningScreenViewed = oldConfig.get("incompatibleModsWarningScreenViewed").asBoolean

        Config.limitsGrappleHitColor = Color(convertHexStringAsAnInt(oldConfigHitColor))
        Config.limitsGrappleMissColor = Color(convertHexStringAsAnInt(oldConfigMissColor))
        Config.flashbackReplayForceAllowIncompatibleMods = oldConfigFlashbackExtreme
        Config.incompatibleModsWarningScreenViewed = oldConfigWarningScreenViewed

        Config.markDirty()
        Config.writeData()

        Winterisms.LOGGER.info("[Winterisms] Old configuration written to a new compatible format! Enjoy! Now deleting old config..")

        oldConfigFile.delete()
    }
}