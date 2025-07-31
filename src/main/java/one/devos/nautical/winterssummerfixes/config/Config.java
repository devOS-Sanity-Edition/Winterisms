package one.devos.nautical.winterssummerfixes.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
    public static String painAndSufferingAndSufferingAndPain = "I understand what I am doing will cause potential instabilities with Flashback. I will not report this to Flashback support. Praise Winter. Praise Moulberry. Praise Ishland.";
    public static final String limitsGrappleCategory = "limitsGrapple";
    public static final String flashbackCategory = "flashback";
    public static final String limitsGrappleModId = "limits_grapple";
    public static final String flashbackModId = "flashback";
    public static final String onboardingCategory = "onboarding";

    @Comment(category = limitsGrappleCategory)
    public static String limitsGrappleSwingColorWindowMissingWarning;

    @Entry(category = limitsGrappleCategory, isColor = true, width = 7, min = 7)
    @Condition(requiredModId = limitsGrappleModId)
    public static String limitsGrappleHitColor = "#3333ff";

    @Entry(category = limitsGrappleCategory, isColor = true, width = 7, min = 7)
    @Condition(requiredModId = limitsGrappleModId)
    public static String limitsGrappleMissColor = "#99994c";

    @Comment(category = flashbackCategory)
    @Condition(requiredModId = flashbackModId)
    public static String flashbackReplayForceAllowIncompatibleModsExtremeWarning;

    @Comment(category = flashbackCategory)
    @Condition(requiredModId = flashbackModId)
    public static String flashbackSpacer;

    @Comment(category = flashbackCategory)
    @Condition(requiredModId = flashbackModId)
    public static String flashbackReplayForceAllowIncompatibleModsExtremeWarning2;

    @Comment(category = flashbackCategory)
    @Condition(requiredModId = flashbackModId)
    public static String flashbackSpacer2;

    @Entry(category = flashbackCategory)
    @Condition(requiredModId = flashbackModId)
    public static String flashbackReplayForceAllowIncompatibleMods = "";

    @Entry(category = onboardingCategory)
    public static Boolean incompatibleModsWarningScreenViewed = false;
}
