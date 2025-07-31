package one.devos.nautical.winterssummerfixes.utils

import net.fabricmc.loader.api.FabricLoader

fun modLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)
fun pairModsLoaded(modId1: String, modId2: String) =
    FabricLoader.getInstance().isModLoaded(modId1) && FabricLoader.getInstance().isModLoaded(modId2)