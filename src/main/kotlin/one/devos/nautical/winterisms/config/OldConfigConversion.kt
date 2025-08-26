package one.devos.nautical.winterisms.config

import com.google.gson.JsonParser
import net.fabricmc.loader.api.FabricLoader
import one.devos.nautical.winterisms.Winterisms
import one.devos.nautical.winterisms.utils.convertHexStringAsAnInt
import java.awt.Color
import java.io.File

fun oldToNewConfigConversation() {
    // start by assigning the old conf file and then checking to see if it exists in the if check
    val oldConfigFile = File(FabricLoader.getInstance().configDir.toFile(), "winterisms.json")

    if (oldConfigFile.exists()) {
        Winterisms.LOGGER.info("[Winterisms] Pre-2.1.2 Winterisms config detected! Lets write it to a compatible format!")

        // grab old data
        val oldConfig = JsonParser.parseString(oldConfigFile.readText()).asJsonObject
        val oldConfigHitColor = oldConfig.get("limitsGrappleHitColor").asString
        val oldConfigMissColor = oldConfig.get("limitsGrappleMissColor").asString
        val oldConfigFlashbackExtreme = oldConfig.get("flashbackReplayForceAllowIncompatibleMods").asString
        val oldConfigWarningScreenViewed = oldConfig.get("incompatibleModsWarningScreenViewed").asBoolean

        // assign old data to current config
        Config.limitsGrappleHitColor = Color(convertHexStringAsAnInt(oldConfigHitColor))
        Config.limitsGrappleMissColor = Color(convertHexStringAsAnInt(oldConfigMissColor))
        Config.flashbackReplayForceAllowIncompatibleMods = oldConfigFlashbackExtreme
        Config.incompatibleModsWarningScreenViewed = oldConfigWarningScreenViewed

        // write
        Config.markDirty()
        Config.writeData()

        Winterisms.LOGGER.info("[Winterisms] Old configuration written to a new compatible format! Enjoy! Now deleting old config..")

        // shoo old data
        oldConfigFile.delete()
    }
}