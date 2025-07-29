package one.devos.nautical.winterssummerfixes.mixin.compat.client.limitsgrapple;

import com.bawnorton.mixinsquared.TargetHandler;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.FastColor;
import one.devos.nautical.winterssummerfixes.config.Config;
import one.devos.nautical.winterssummerfixes.utils.ConversionKt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@SuppressWarnings("MixinAnnotationTarget")
@Mixin(value = Gui.class, priority = 1050)
@IfModLoaded("limits_grapple")
public class LimitsGrappleInGameHudColorMixin {
    @TargetHandler(mixin = "io.github.moonlight_maya.limits_grapple.mixin.render.InGameHudMixin", name = "limits_grapple$drawHitResult")
    @ModifyArgs(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;setColor(FFFF)V", ordinal = 0))
    private void modifyHitColor(Args args) {
        int hitColor = ConversionKt.convertHexStringAsAnInt(Config.limitsGrappleHitColor);

        args.set(0, FastColor.ARGB32.red(hitColor) / 255f);
        args.set(1, FastColor.ARGB32.green(hitColor) / 255f);
        args.set(2, FastColor.ARGB32.blue(hitColor) / 255f);
        args.set(3, 1.0f);
    }

    @TargetHandler(mixin = "io.github.moonlight_maya.limits_grapple.mixin.render.InGameHudMixin", name = "limits_grapple$drawHitResult")
    @ModifyArgs(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;setColor(FFFF)V", ordinal = 1))
    private void modifyMissColor(Args args) {
        int missColor = ConversionKt.convertHexStringAsAnInt(Config.limitsGrappleMissColor);

        args.set(0, FastColor.ARGB32.red(missColor) / 255f);
        args.set(1, FastColor.ARGB32.green(missColor) / 255f);
        args.set(2, FastColor.ARGB32.blue(missColor) / 255f);
        args.set(3, 1.0f);
    }
}
