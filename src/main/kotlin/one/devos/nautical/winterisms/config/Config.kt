package one.devos.nautical.winterisms.config

import gay.asoji.fmw.FMW
import gg.essential.vigilance.Vigilant
import net.fabricmc.loader.api.FabricLoader
import one.devos.nautical.winterisms.Winterisms
import one.devos.nautical.winterisms.utils.translate
import java.awt.Color
import java.awt.Component
import java.io.File

object Config : Vigilant(
    File("${FabricLoader.getInstance().configDir}/winterisms.toml"),
    "${FMW.getName(Winterisms.MOD_ID)} v${FMW.getVersion(Winterisms.MOD_ID)}"
) {
    var painAndSufferingAndSufferingAndPain: String =
        "I understand what I am doing will cause potential instabilities with Flashback. I will not report this to Flashback support. Praise Winter. Praise Moulberry. Praise Ishland."
    var limitsGrappleHitColor: Color = Color(51, 51, 255)
    var limitsGrappleMissColor: Color = Color(153, 153, 76)
    var flashbackReplayForceAllowIncompatibleMods: String = ""
    var incompatibleModsWarningScreenViewed: Boolean = false

    init {
        category(translate("winterisms.config.category.modifications")) {
            subcategory(translate("winterisms.config.subcategory.limitsGrapple")) {
                color(::limitsGrappleHitColor,
                    translate("winterisms.config.limitsGrapple.hitColor.title"),
                    translate("winterisms.config.limitsGrapple.hitColor.description"),
                    allowAlpha = false
                )
                color(::limitsGrappleMissColor,
                    translate("winterisms.config.limitsGrapple.missColor.title"),
                    translate("winterisms.config.limitsGrapple.missColor.description"),
                    allowAlpha = false
                )
            }

            subcategory(translate("winterisms.config.subcategory.flashback")) {
                paragraph(::flashbackReplayForceAllowIncompatibleMods,
                    translate("winterisms.config.flashbackReplayForceAllowIncompatibleMods"), "${translate<String>("winterisms.config.flashbackReplayForceAllowIncompatibleModsExtremeWarning")} \n${
                        translate<String>("winterisms.config.flashbackReplayForceAllowIncompatibleModsExtremeWarning2")
                    }", hidden = !FabricLoader.getInstance().isModLoaded("flashback"))
            }
        }

        category(translate("winterisms.config.category.onboarding")) {
            switch(::incompatibleModsWarningScreenViewed,
                translate("winterisms.config.incompatibleModsWarningScreenViewed.title"),
                translate("winterisms.config.incompatibleModsWarningScreenViewed.description")
            )
        }

        initialize()
    }
}