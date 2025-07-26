package one.devos.nautical.winterssummerfixes

import gay.asoji.fmw.FMW
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object WintersSummerFixes : ModInitializer {
    val MOD_ID: String = "winterssummerfixes"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    val MOD_NAME: String = FMW.getName(MOD_ID)

    override fun onInitialize() {
        LOGGER.info("[${MOD_NAME}] Winter's Summer Fixes v${FMW.getVersion(MOD_ID)} loaded!")
        LOGGER.info("[${MOD_NAME}] Who let the gay cat furry into the server room? Get them out! Oh wait they have a wrench.")
    }
}