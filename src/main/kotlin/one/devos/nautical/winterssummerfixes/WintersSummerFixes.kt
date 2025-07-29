package one.devos.nautical.winterssummerfixes

import eu.midnightdust.lib.config.MidnightConfig
import gay.asoji.fmw.FMW
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import one.devos.nautical.winterssummerfixes.config.Config
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object WintersSummerFixes : ModInitializer {
    val MOD_ID: String = "winterssummerfixes"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    val MOD_NAME: String = FMW.getName(MOD_ID)

    val ROOMBA_SOUND_EVENT: SoundEvent = Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(MOD_ID, "roomba"),
        SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "roomba")))

    override fun onInitialize() {
        Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(MOD_ID, "roomba"),
            SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "roomba")))
        MidnightConfig.init(MOD_ID, Config::class.java)
        LOGGER.info("[${MOD_NAME}] Winter's Summer Fixes v${FMW.getVersion(MOD_ID)} loaded!")
        LOGGER.info("[${MOD_NAME}] Who let the gay cat furry into the server room? Get them out! Oh wait they have a wrench.")
    }
}