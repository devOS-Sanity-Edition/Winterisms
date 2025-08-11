package one.devos.nautical.winterisms.mixin.compat.journeymap;

import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import journeymap.client.waypoint.WaypointParser;
import one.devos.nautical.winterisms.utils.ConversionKt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@IfModLoaded("journeymap")
@Mixin(value = WaypointParser.class, remap = false)
public abstract class WaypointParserMixin {
    @Redirect(method = "getWaypointStrings", at = @At(value = "INVOKE", target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z"))
    private static boolean fuckOff(String instance, String prefix) {
        return instance.contains(prefix);
    }

    @ModifyVariable(method = "getWaypointStrings", at = @At(value = "INVOKE", target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z"), argsOnly = true)
    private static String fixParsingXaeros(String instance) {
        return ConversionKt.fixXaerosString(instance);
    }
}
