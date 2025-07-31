package one.devos.nautical.winterssummerfixes.client.screens

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.ChatFormatting
import net.minecraft.client.gui.components.MultiLineTextWidget
import net.minecraft.client.gui.components.StringWidget
import net.minecraft.client.gui.layouts.LinearLayout
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.gui.screens.options.OptionsSubScreen
import net.minecraft.network.chat.Component
import one.devos.nautical.winterssummerfixes.config.Config
import one.devos.nautical.winterssummerfixes.utils.modLoaded
import one.devos.nautical.winterssummerfixes.utils.pairModsLoaded

class InitialIncompatibleWarningScreen(lastScreen: Screen) :
    OptionsSubScreen(
        lastScreen,
        null,
        Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.title")
    ) {
        var tripped: Boolean = false

    override fun addContents() {
        val linearLayout = LinearLayout.vertical()
        this.layout.addToContents(linearLayout)
        if (pairModsLoaded("c2me", "flashback")) {
            tripped = true
            linearLayout.addChild(
                StringWidget(
                    Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.c2meAndFlashbackFoundTitle").withStyle(ChatFormatting.BOLD), this.font
                ).alignCenter()
            )
            linearLayout.addChild(
                MultiLineTextWidget(
                    Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.c2meAndFlashbackFoundDescription"),
                    this.font
                ).setMaxWidth(width - 10)
            )
            linearLayout.addChild(StringWidget(Component.empty(), this.font))
        }

        if (modLoaded("centered-crosshair")) {
            tripped = true
            linearLayout.addChild(
                StringWidget(
                    Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.centeredCrosshairFoundTitle").withStyle(ChatFormatting.BOLD), this.font
                ).alignCenter()
            )
            linearLayout.addChild(
                MultiLineTextWidget(
                    Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.centeredCrosshairFoundDescription"),
                    this.font
                ).setMaxWidth(width - 10)
            )
            linearLayout.addChild(StringWidget(Component.empty(), this.font))
        }

        if (pairModsLoaded("jade", "starhud")) {
            tripped = true
            linearLayout.addChild(
                StringWidget(
                    Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.jadeAndStarHUDFoundTitle").withStyle(ChatFormatting.BOLD), this.font
                ).alignCenter()
            )
            linearLayout.addChild(
                MultiLineTextWidget(
                    Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.jadeAndStarHUDFoundDescription"),
                    this.font
                ).setMaxWidth(width - 10)
            )
            linearLayout.addChild(StringWidget(Component.empty(), this.font))
        }

        if (!modLoaded("iris")) {
            tripped = true
            linearLayout.addChild(
                StringWidget(
                    Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.irisMissingTitle").withStyle(ChatFormatting.BOLD), this.font
                ).alignCenter()
            )
            linearLayout.addChild(
                MultiLineTextWidget(
                    Component.translatable("winterssummerfixes.screen.initialIncompatibleWarningScreen.irisMissingDescription"),
                    this.font
                ).setMaxWidth(width - 10)
            )
            linearLayout.addChild(StringWidget(Component.empty(), this.font))
        }

        if (!tripped) {
            onClose()
        }
    }

    override fun addOptions() {}
    override fun onClose() {
        Config.incompatibleModsWarningScreenViewed = true
        this.minecraft?.setScreen(this.lastScreen)
    }
}