package one.devos.nautical.winterssummerfixes.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import one.devos.nautical.winterssummerfixes.WintersSummerFixes

object WintersSummerFixesClient : ClientModInitializer {
    override fun onInitializeClient() {
        ResourceManagerHelper.registerBuiltinResourcePack(
            ResourceLocation.fromNamespaceAndPath(WintersSummerFixes.MOD_ID, "othertexture"),
            FabricLoader.getInstance().getModContainer(WintersSummerFixes.MOD_ID).get(),
            Component.literal("Alt Raccoon Trinket Texture"),
            ResourcePackActivationType.NORMAL
        )
    }
}