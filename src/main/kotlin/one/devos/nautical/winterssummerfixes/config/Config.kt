package one.devos.nautical.winterssummerfixes.config

import com.google.gson.GsonBuilder
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler
import dev.isxander.yacl3.config.v2.api.SerialEntry
import dev.isxander.yacl3.config.v2.api.autogen.ColorField
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.resources.ResourceLocation
import one.devos.nautical.winterssummerfixes.WintersSummerFixes
import java.awt.Color

object Config {
//    val HANDLER: ConfigClassHandler<Config> = ConfigClassHandler.createBuilder(Config::class.java)
//        .id(ResourceLocation.fromNamespaceAndPath(WintersSummerFixes.MOD_ID, "config"))
//        .serializer { config ->
//            GsonConfigSerializerBuilder.create(config)
//                .setPath(FabricLoader.getInstance().configDir.resolve("winterssummerfixes.json5"))
//                .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
//                .setJson5(true)
//                .build()
//        }.build()
//
    @ColorField
    @SerialEntry
    var limitsGrappleHitColor: Color = Color(202f / 255f, 255f / 255f, 191f / 255f)

    @ColorField
    @SerialEntry
    var limitsGrappleMissColor: Color = Color(255f / 255f, 173f / 255f, 173f / 255f)
}