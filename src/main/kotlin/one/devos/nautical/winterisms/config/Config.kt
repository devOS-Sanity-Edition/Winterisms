package one.devos.nautical.winterisms.config

import gay.asoji.fmw.FMW
import gg.essential.vigilance.Vigilant
import net.fabricmc.loader.api.FabricLoader
import one.devos.nautical.winterisms.Winterisms
import one.devos.nautical.winterisms.utils.translate
import java.awt.Color
import java.io.File

object Config : Vigilant(
    File(FabricLoader.getInstance().configDir.toFile(), "winterisms.toml"),
    "${FMW.getName(Winterisms.MOD_ID)} v${FMW.getVersion(Winterisms.MOD_ID)}"
) {
    var painAndSufferingAndSufferingAndPain: String =
        "I understand what I am doing will cause potential instabilities with Flashback. I will not report this to Flashback support. Praise Winter. Praise Moulberry. Praise Ishland."
    var limitsGrappleHitColor: Color = Color(51, 51, 255)
    var limitsGrappleMissColor: Color = Color(153, 153, 76)
    var flashbackReplayForceAllowIncompatibleMods: String = ""
    var incompatibleModsWarningScreenViewed: Boolean = false

    init {
        // all plans of using internationalization has fallen apart so we have to hard code for now, at least until
        // there's a way you can custom define in the config what a key is, so forgive me for hardcoding this mfer
        category("Mod Modifications") {
            subcategory("Limits' Grapple") {
                color(::limitsGrappleHitColor,
                    "Hit Color",
                    "Color that shows on your Grappling HUD when you're able to grapple.",
                    allowAlpha = false
                )
                color(::limitsGrappleMissColor,
                    "Miss Color",
                    "Color that shows on your Grappling HUD when you're not able to grapple.",
                    allowAlpha = false
                )
            }

            subcategory("Flashback") {
                paragraph(::flashbackReplayForceAllowIncompatibleMods,
                    "Force Allow Incompatible Mods",
                    "§cWarning: Don't touch this unless you know what you're doing. Flashback disables this for a very good reason. If you do know exactly what you're doing, then enter the following:\n\n§rI understand what I am doing will cause potential instabilities with Flashback. I will not report this to Flashback support. Praise Winter. Praise Moulberry. Praise Ishland.", hidden = !FabricLoader.getInstance().isModLoaded("flashback"))
            }
        }

        category("Onboarding") {
            switch(::incompatibleModsWarningScreenViewed,
                "Problem Mods Screen Viewed?",
                "If viewed once, will be switched on implying that the screen has been seen. If you want to see it again, switch it off, and then back out of this menu."
            )
        }



        initialize()


    }
}