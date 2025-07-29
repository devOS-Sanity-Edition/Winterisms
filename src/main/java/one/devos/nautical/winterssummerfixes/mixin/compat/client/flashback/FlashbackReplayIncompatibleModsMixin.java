package one.devos.nautical.winterssummerfixes.mixin.compat.client.flashback;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moulberry.flashback.Flashback;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundEvents;
import one.devos.nautical.winterssummerfixes.WintersSummerFixes;
import one.devos.nautical.winterssummerfixes.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Objects;

@Mixin(value = Flashback.class, priority = 1800)
@IfModLoaded("flashback")
public class FlashbackReplayIncompatibleModsMixin {
    @Unique
    private static void playRoombaSound(SoundManager handler) {
        handler.play(SimpleSoundInstance.forUI(WintersSummerFixes.INSTANCE.getROOMBA_SOUND_EVENT(), 1.0F));
    }

    @ModifyReturnValue(
            method = "getReplayIncompatibleMods",
            at = @At(value = "RETURN",
                    target = "Lcom/moulberry/flashback/Flashback;getReplayIncompatibleMods()Ljava/util/List;"
            ),
            remap = false
    )
    private static List<String> getReplayIncompatibleMods(List<String> original) {
        if (Objects.equals(Config.flashbackReplayForceAllowIncompatibleMods, Config.painAndSufferingAndSufferingAndPain)) {
            playRoombaSound(Minecraft.getInstance().getSoundManager());
            original.clear();
        }

        return original;
    }
}
