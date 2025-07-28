package one.devos.nautical.winterssummerfixes.config

import dev.isxander.yacl3.api.Binding
import dev.isxander.yacl3.api.controller.ColorControllerBuilder
import dev.isxander.yacl3.config.v3.JsonFileCodecConfig
import dev.isxander.yacl3.config.v3.register
import dev.isxander.yacl3.dsl.YetAnotherConfigLib
import dev.isxander.yacl3.dsl.binding
import dev.isxander.yacl3.dsl.controller
import dev.isxander.yacl3.dsl.text
import dev.isxander.yacl3.platform.YACLPlatform
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import one.devos.nautical.winterssummerfixes.WintersSummerFixes
import org.apache.http.client.methods.RequestBuilder.options
import java.awt.Color

object ConfigScreens : JsonFileCodecConfig<ConfigScreens>(YACLPlatform.getConfigDir().resolve("winterssummerfixes.json5")) {
//    init {
//        if (!loadFromFile()) {
//            saveToFile()
//        }
//    }
//
//    fun generateConfigScreen(lastScreen: Screen?) = YetAnotherConfigLib(WintersSummerFixes.MOD_ID) {
//        title(Component.translatable("winterssummerfixes.config.title"))
//
//        save {
//            saveToFile()
//        }
//
//        val clientCategory by categories.registering("client") {
//            name(Component.translatable("winterssummerfixes.config.client"))
//
//            groups.register("grapplingHook") {
//                name(Component.translatable("winterssummerfixes.config.config.client.grapplingHook"))
//
//                val grapplingHookHitColor by options.registering<Color> {
//                    binding(Config.limitsGrappleHitColor as Binding<Color?>)
//                    controller = ColorControllerBuilder::create
//                }
//            }
//        }
//    }.generateScreen(lastScreen)
}