package one.devos.nautical.winterisms.client

import com.illusivesoulworks.polymorph.api.client.PolymorphWidgets
import com.illusivesoulworks.polymorph.api.client.widgets.FurnaceRecipesWidget
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import one.devos.nautical.winterisms.Winterisms
import one.devos.nautical.winterisms.config.Config
import one.devos.nautical.winterisms.utils.UnsupIni
import techreborn.client.gui.GuiElectricFurnace

object WinterismsClient : ClientModInitializer {
    override fun onInitializeClient() {
        if (UnsupIni.unsupIniFile.exists()) {
            Config.modpackTitle = UnsupIni.brandingTitle
            Config.modpackQOIBase64Data = UnsupIni.brandingQOIBase64String
        }

//        Winterisms.LOGGER.info(Config.modpackTitle)
//        Winterisms.LOGGER.info(Config.modpackQOIBase64Data)
//        Winterisms.LOGGER.info(brotliToByteArray(base64ToBrotliInputStream(Config.modpackQOIBase64Data)).toHexString())
//
//        altogetherNowTestFromBase64StringToFinalQOIImage(Config.modpackQOIBase64Data)

        ResourceManagerHelper.registerBuiltinResourcePack(
            ResourceLocation.fromNamespaceAndPath(Winterisms.MOD_ID, "othertexture"),
            FabricLoader.getInstance().getModContainer(Winterisms.MOD_ID).get(),
            Component.literal("Alt Raccoon Trinket Texture"),
            ResourcePackActivationType.NORMAL
        )

        PolymorphWidgets.getInstance().registerWidget { screen ->
            if (screen is GuiElectricFurnace) {
                return@registerWidget FurnaceRecipesWidget(screen)
            }

            null
        }

        Minecraft.getInstance().execute { Minecraft.getInstance().window.setTitle(Config.modpackTitle) }
    }
}