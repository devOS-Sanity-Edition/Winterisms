package one.devos.nautical.winterisms.mixin.client.compat.flashback;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moulberry.flashback.Flashback;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import one.devos.nautical.winterisms.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Objects;

@Mixin(value = Flashback.class, priority = 1800)
@IfModLoaded("flashback")
public class FlashbackReplayIncompatibleModsMixin {
    @ModifyReturnValue(
            method = "getReplayIncompatibleMods",
            at = @At(value = "RETURN",
                    target = "Lcom/moulberry/flashback/Flashback;getReplayIncompatibleMods()Ljava/util/List;"
            ),
            remap = false
    )
    private static List<String> getReplayIncompatibleMods(List<String> original) {
        if (Objects.equals(Config.INSTANCE.getFlashbackReplayForceAllowIncompatibleMods(), Config.INSTANCE.getPainAndSufferingAndSufferingAndPain())) {
            original.clear();
        }

        return original;
    }
}
