package one.devos.nautical.winterssummerfixes.mixin.compat.client.multikeybindings;

import com.bawnorton.mixinsquared.TargetHandler;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Multi Key Bindings logs on every mouse click in a UI, so we're just gonna null and void that. Why that logger call is
// left there, I have no fucking clue.
@IfModLoaded("multi-key-bindings")
@Mixin(value = AbstractContainerScreen.class, priority = 1500)
public class MultiKeyBindingsHandledScreenMixin {
    @TargetHandler(mixin = "us.kenny.mixin.HandledScreenMixin", name = "onMouseClicked")
    @Redirect(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;info(Ljava/lang/String;)V"))
    private void goAwayUselessLogSpam(Logger instance, String s) {

    }
}
