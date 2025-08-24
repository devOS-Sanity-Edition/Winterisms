package one.devos.nautical.winterisms.config

import gay.asoji.fmw.FMW
import gg.essential.vigilance.Vigilant
import net.fabricmc.loader.api.FabricLoader
import one.devos.nautical.winterisms.Winterisms
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
    var xaerosJourneyFix: Boolean = true
    var modpackTitle: String = "Winter's Summer"
    var modpackQOIData: String = ""
    var gAIDeterrent: Boolean = true
    var uploadCrashToMCLogs: Boolean = true
    var openBrowserOnGameCrash: Boolean = true

    init {
        // all plans of using internationalization has fallen apart so we have to hard code for now, at least until
        // there's a way you can custom define in the config what a key is, so forgive me for hardcoding this mfer
        category("Modifications") {
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
                    "§cWarning: Don't touch this unless you know what you're doing. Flashback disables this for a very good reason. If you do know exactly what you're doing, then enter the following:\n\n§rI understand what I am doing will cause potential instabilities with Flashback. I will not report this to Flashback support. Praise Winter. Praise Moulberry. Praise Ishland.", hidden = !FabricLoader.getInstance().isModLoaded("flashback")
                )
            }
        }

        category("Fixes") {
            subcategory("Map Mods") {
                switch(::xaerosJourneyFix,
                    "Allow Waypoint Chat Cross-Compat",
                    "This allows either waypoints to be clickable in chat, as if it was native to said map mod, meaning if you're on Xaero's, you can click JourneyMap Waypoints, and same for the reverse."
                )
            }
        }

        category("Winter's Modpacks") {
            subcategory("Onboarding") {
                switch(::incompatibleModsWarningScreenViewed,
                    "Problem Mods Screen Viewed?",
                    "If viewed once, will be switched on implying that the screen has been seen. If you want to see it again, switch it off, and then back out of this menu."
                )
            }

            subcategory("Pack Settings") {

                text(::modpackTitle,
                    "Modpack Title",
                    "The name of the Modpack. Used for Window title and a few other things. Recommended to not change it unless you're making a brand new modpack, like devOS: Season 7 or something.\n\n§6Requires game restart."
                )

                paragraph(::modpackQOIData,
                    "Modpack QOI Data String",
                    "The Base64, Brotli-compressed QOI Data string for the server icon, used by Unsup by default.\n\n§6Requires game restart."
                )

                switch(::gAIDeterrent,
                    "Generative AI Deterrent in Crash Logs",
                    "Injects in Crash Logs a prompt that tells Generative AI providers to not have game crash logs read by Generative AI, and to go report to Mod Developers instead."
                )

                switch(::uploadCrashToMCLogs,
                    "Upload crash to mclogs service",
                    "Uploads game crash to mclo.gs service, while preserving your privacy and redacting all private information. Useful if pack crashes quite a bit and need to get logs to a developer."
                )

                switch(::openBrowserOnGameCrash,
                    "Open Browser on Crash",
                    "On game crash, and §6Upload crash to mclogs§r is enabled, the player's browser will open to the log link."
                )
            }
        }

        setSubcategoryDescription("Winter's Modpacks", "Pack Settings", "§cWarning: §rPlease do not touch any of these settings. They're here as easy exposure for modpack creators, but unless you know what you're doing or have a good reason to change them, please do not change any of these settings.\n\n§6If an unsup.ini exists in your pack's game directory, that will override whatever you put here. Modify the unsup.ini, or don't have one if you want to change the pack title and/or icon.")

        initialize()
    }
}