package one.devos.nautical.winterssummerfixes.mixin.compat.client.flashback;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moulberry.flashback.Flashback;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import one.devos.nautical.winterssummerfixes.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@IfModLoaded("flashback")
@Mixin(value = Flashback.class, priority = 1800, remap = false)
public class FlashbackReplayIncompatibleModsMixin {
    @ModifyReturnValue(
            method = "getReplayIncompatibleMods",
            at = @At(value = "RETURN",
                    target = "Lcom/moulberry/flashback/Flashback;getReplayIncompatibleMods()Ljava/util/List;"
            )
    )
    private static List<String> getReplayIncompatibleMods(List<String> original) {
        if (Config.flashbackReplayForceAllowIncompatibleMods) {
            original.clear();
        }

        return original;
    }
}
