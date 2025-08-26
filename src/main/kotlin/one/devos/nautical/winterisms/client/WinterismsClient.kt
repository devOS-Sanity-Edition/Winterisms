package one.devos.nautical.winterisms.client

import com.illusivesoulworks.polymorph.api.client.PolymorphWidgets
import com.illusivesoulworks.polymorph.api.client.widgets.FurnaceRecipesWidget
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import one.devos.nautical.winterisms.Winterisms
import techreborn.client.gui.GuiElectricFurnace

object WinterismsClient : ClientModInitializer {
    override fun onInitializeClient() {
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
    }
}