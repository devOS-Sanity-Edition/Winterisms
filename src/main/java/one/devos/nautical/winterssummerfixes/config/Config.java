package one.devos.nautical.winterssummerfixes.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
    public static String painAndSufferingAndSufferingAndPain = "I understand what I am doing will cause potential instabilities with Flashback. I will not report this to Flashback support. Praise Winter. Praise Moulberry. Praise Ishland.";

    @Entry(category = "limitsGrapple", isColor = true)
    public static String limitsGrappleHitColor = "#3333ff";

    @Entry(category = "limitsGrapple", isColor = true)
    public static String limitsGrappleMissColor = "#99994c";

    @Comment(category = "flashback")
    @Condition(requiredModId = "flashback")
    public static String flashbackReplayForceAllowIncompatibleModsExtremeWarning;

    @Comment(category = "flashback")
    @Condition(requiredModId = "flashback")
    public static String flashbackSpacer;

    @Comment(category = "flashback")
    @Condition(requiredModId = "flashback")
    public static String flashbackReplayForceAllowIncompatibleModsExtremeWarning2;

    @Comment(category = "flashback")
    @Condition(requiredModId = "flashback")
    public static String flashbackSpacer2;

    @Entry(category = "flashback")
    @Condition(requiredModId = "flashback")
    public static String flashbackReplayForceAllowIncompatibleMods = "";
}
