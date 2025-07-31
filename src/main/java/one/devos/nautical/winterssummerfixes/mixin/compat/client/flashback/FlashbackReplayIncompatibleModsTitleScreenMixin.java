package one.devos.nautical.winterssummerfixes.mixin.compat.client.flashback;

import com.bawnorton.mixinsquared.TargetHandler;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = TitleScreen.class, priority = 1800)
@IfModLoaded("flashback")
public class FlashbackReplayIncompatibleModsTitleScreenMixin {
    @TargetHandler(mixin = "com.moulberry.flashback.mixin.ui.MixinTitleScreen", name = "lambda$createOrPositionOpenSelectReplayScreenButton$1")
    @ModifyArg(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;", ordinal = 0))
    private static String modifyWarning(String key) {
        return "winterssummerfixes.flashback.incompatibleWithRecordingDescription";
    }
}
