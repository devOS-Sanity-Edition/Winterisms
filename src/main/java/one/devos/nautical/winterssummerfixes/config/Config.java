package one.devos.nautical.winterssummerfixes.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
    @Comment(category = "client", centered = true)
    public static String limitsGrapple;

    @Entry(category = "client", isColor = true)
    public static String limitsGrappleHitColor = "#3333ff";

    @Entry(category = "client", isColor = true)
    public static String limitsGrappleMissColor = "#99994c";

    @Comment(category = "client", centered = true)
    public static String flashback;

    @Comment(category = "client", centered = true)
    public static String flashbackSpacer;

    @Comment(category = "client")
    public static String flashbackReplayForceAllowIncompatibleModsExtremeWarning;

    @Entry(category = "client")
    public static Boolean flashbackReplayForceAllowIncompatibleMods = false;
}
