package one.devos.nautical.winterssummerfixes.mixin.compat.client;

import com.bawnorton.mixinsquared.TargetHandler;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import net.minecraft.client.gui.Gui;
import one.devos.nautical.winterssummerfixes.config.Config;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@SuppressWarnings("MixinAnnotationTarget")
@Mixin(value = Gui.class, priority = 1050)
public class LimitsGrappleInGameHudColorMixin {
    @TargetHandler(mixin = "io.github.moonlight_maya.limits_grapple.mixin.render.InGameHudMixin", name = "limits_grapple$drawHitResult")
    @ModifyArgs(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;setColor(FFFF)V", ordinal = 0))
    private void modifyHitColor(Args args) {
        float[] hitColorFromConfig = Config.INSTANCE.getLimitsGrappleHitColor().getColorComponents(null);
        Vector4f hitColor = new Vector4f(hitColorFromConfig[0], hitColorFromConfig[1], hitColorFromConfig[2], 1f);

        args.set(0, hitColor.x);
        args.set(1, hitColor.y);
        args.set(2, hitColor.z);
        args.set(3, hitColor.w);
    }

    @TargetHandler(mixin = "io.github.moonlight_maya.limits_grapple.mixin.render.InGameHudMixin", name = "limits_grapple$drawHitResult")
    @ModifyArgs(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;setColor(FFFF)V", ordinal = 1))
    private void modifyMissColor(Args args) {
        float[] missColorFromConfig = Config.INSTANCE.getLimitsGrappleMissColor().getColorComponents(null);
        Vector4f missColor = new Vector4f(missColorFromConfig[0], missColorFromConfig[1], missColorFromConfig[2], 1f);

        args.set(0, missColor.x);
        args.set(1, missColor.y);
        args.set(2, missColor.z);
        args.set(3, missColor.w);
    }
}
