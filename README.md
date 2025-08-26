# Winterisms

Some fixes done for the Winter's modpacks.

## Fixes [so far]
- Allow the Grappling Hook from Limits' Grapple fork to have Durability-based enchantments [Unbreaking, Mending, etc]
- Allow Flashback's replay viewer to work with incompatible mods [VMP, C2ME] via config
- All Vaults in Enderscape's End Ships are replaced with Enderscape's End Vault, guaranteeing Elytra
- Flashback should no longer crash, due to Enderscape expecting the End
- Xaero's format should be clickable again in JourneyMap [JourneyMap added support for Xaero's but it broke at some point?]

## Additions [so far]
- Raccoon trinkets slot for Raccoons & Rabies
- Be able to change the Grappling Hook's HUD color through the config
- Potential Problem Mods screen on first startup
- Be able to click on Journeymap formatted waypoints if you use Xaero's [Journey already integrates the other way around]
- Everyone in the end during an end fight gets a dragon egg
- Dark Titlebar [Windows 11 only]
- Ability to change titlebar and icon [Must be a 32x32 Brotli-compressed QOI Image in Base64 string form] [Converter here](https://qoi.y2k.diy/)
- Ability to read unsup.ini files in pack folder to set icon [Must be a 32x32 Brotli-compressed QOI Image in Base64 string form] and titlebar
- `request` commands, notably `request dontSleep`, `request attention` and [OP Level 4 only] `request serverRestart`
- `restart` command that saves, does a 15 second countdown, and then stops server [and let Pelican/Pterodactyl start server back up under assumption]
- Generative AI deterrent in logs
- Uploads crash logs automatically and opens up browser

A lot of this can be found in the mod's config, which is powered by [Vigilance](https://github.com/EssentialGG/Vigilance)

![java_axPRlxeTJb](https://github.com/user-attachments/assets/503969a7-b709-4ccd-baac-2195fc5fca29)

## Credits
- BluSpring for.. basically half of this mod and why it even exists. Major major code help.
- Cartrigger for idea of Raccoon trinket slot and the 2 raccoon slot textures
- CephalonCosmic for internal tool to figure out which NBT files have certain values, and fixing the internal impl for getting QOI Images working correctly
- Deftu for Vigilance config assistance and better server restart command implementation
- IThundxr for 
- maximumpower55 for helping with the pack, and the mclo.gs crash handling from TeaBridge
- Oliver-makes-code for showing how to setup datapacks as a resource pack for actual working Structure NBT overriding
- IMS212 for end dragon fight mixin

## License
As per usual with all my projects, [MIT License](LICENSE).

However, the [CrashReportGAIDeterrentMixin](src/main/java/one/devos/nautical/winterisms/mixin/common/CrashReportGenerativeAIDeterrentMixin.java) in specific is 0BSD, feel free to take it, no attributions needed. :p