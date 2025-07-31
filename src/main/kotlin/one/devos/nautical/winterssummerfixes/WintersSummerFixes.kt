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
    var PROBLEM_CHECK: Boolean = false // unused atm

    override fun onInitialize() {
        // Despite the fucking name, this actually can load Datapack bullshit as well! As Octal said, `Fabric is just
        // bad at naming things most of the time`. ugh. So we're trying to fucking override it as a resource pack
        // instead of the data folder in the fucking mod itself because priority i guess? fucking hell lmao
        // https://github.com/FabricMC/fabric/tree/1.21.1/fabric-resource-loader-v0/src/testmod/resources/resourcepacks/test2
        ResourceManagerHelper.registerBuiltinResourcePack(
            ResourceLocation.fromNamespaceAndPath(WintersSummerFixes.MOD_ID, "enderscape"),
            FabricLoader.getInstance().getModContainer(WintersSummerFixes.MOD_ID).get(),
            Component.literal("Enderscape Data Fixes"),
            ResourcePackActivationType.ALWAYS_ENABLED
        )

        MidnightConfig.init(MOD_ID, Config::class.java)
        LOGGER.info("[${MOD_NAME}] Winter's Summer Fixes v${FMW.getVersion(MOD_ID)} loaded!")
        LOGGER.info("[${MOD_NAME}] Who let the gay cat furry into the server room? Get them out! Oh wait they have a wrench.")
    }
}