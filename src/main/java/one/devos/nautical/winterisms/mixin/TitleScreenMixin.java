package one.devos.nautical.winterisms.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import one.devos.nautical.winterisms.client.screens.InitialIncompatibleWarningScreen;
import one.devos.nautical.winterisms.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void init(CallbackInfo info) {
        if (Config.incompatibleModsWarningScreenViewed == false) {
            this.minecraft.setScreen(new InitialIncompatibleWarningScreen(this));
        }
    }
}
