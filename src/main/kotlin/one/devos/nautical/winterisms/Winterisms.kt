package one.devos.nautical.winterisms

import com.illusivesoulworks.polymorph.api.PolymorphApi
import gay.asoji.fmw.FMW
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import one.devos.nautical.winterisms.commands.requestCommand
import one.devos.nautical.winterisms.commands.restartCommand
import one.devos.nautical.winterisms.compat.polymorph.techreborn.TRElectricFurnaceDataComponent
import one.devos.nautical.winterisms.config.oldToNewConfigConversation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import techreborn.blockentity.machine.tier1.ElectricFurnaceBlockEntity
import java.io.File

object Winterisms : ModInitializer {
    val MOD_ID: String = "winterisms"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
    val MOD_NAME: String = FMW.getName(MOD_ID)
    var PROBLEM_CHECK: Boolean = false // unused atm

    override fun onInitialize() {
        if (File(FabricLoader.getInstance().configDir.toFile(), "winterisms.json").exists()) oldToNewConfigConversation()

        // Despite the fucking name, this actually can load Datapack bullshit as well! As Octal said, `Fabric is just
        // bad at naming things most of the time`. ugh. So we're trying to fucking override it as a resource pack
        // instead of the data folder in the fucking mod itself because priority i guess? fucking hell lmao
        // https://github.com/FabricMC/fabric/tree/1.21.1/fabric-resource-loader-v0/src/testmod/resources/resourcepacks/test2
        ResourceManagerHelper.registerBuiltinResourcePack(
            ResourceLocation.fromNamespaceAndPath(MOD_ID, "enderscape"),
            FabricLoader.getInstance().getModContainer(MOD_ID).get(),
            Component.literal("Enderscape Data Fixes"),
            ResourcePackActivationType.ALWAYS_ENABLED
        )

        PolymorphApi.getInstance().registerBlockEntity { blockEntity ->
            if (blockEntity is ElectricFurnaceBlockEntity) {
                return@registerBlockEntity TRElectricFurnaceDataComponent(blockEntity)
            }

            null
        }

        CommandRegistrationCallback.EVENT.register { dispatcher, registryAccess, environment ->
            requestCommand(dispatcher)
            restartCommand(dispatcher)
        }

        LOGGER.info("[${MOD_NAME}] Winterisms v${FMW.getVersion(MOD_ID)} loaded!")
        LOGGER.info("[${MOD_NAME}] Who let the gay cat furry into the server room? Get them out! Oh wait they have a wrench.")
    }
}