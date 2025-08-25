package one.devos.nautical.winterisms.mixin.client;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class DisableVanillaTitle {
    @Inject(method = "updateTitle", at = @At("HEAD"), cancellable = true)
    private void disableVanillaTitle(final CallbackInfo ci) {
        ci.cancel();
    }
}
