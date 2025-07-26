package one.devos.nautical.winterssummerfixes.mixin.client;

import com.bawnorton.mixinsquared.TargetHandler;
import com.moulberry.mixinconstraints.annotations.IfModAbsent;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import me.wolfii.DrawContextFloatDrawTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// If used with Centered Crosshair, Limits' Grapple's HUD isn't centered as well, so lets fix that. Dynamic Crosshair already takes care of this I believe?
// TODO: DEAR GOD HELP US THIS HAS BEEN A 1+ HR NIGHTMARE BEYOND BELIEF, PLEASE COME BACK NAZ
@SuppressWarnings("MixinAnnotationTarget")
@Mixin(value = Gui.class, priority = 1050)
@IfModLoaded(value = "centered-crosshair")
@IfModAbsent(value = "dynamiccrosshair")
public class LimitsGrappleInGameHudMixin {
    @Shadow
    @Final
    private static int limits_grapple$CROSSHAIR_TEXTURE_WIDTH;

    @Shadow
    @Final
    private static int limits_grapple$CROSSHAIR_TEXTURE_HEIGHT;

    @TargetHandler(mixin = "io.github.moonlight_maya.limits_grapple.mixin.render.InGameHudMixin", name = "limits_grapple$drawHitResult")
    @Redirect(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIFFIIII)V"))
    private void modifyXValue(GuiGraphics instance, ResourceLocation atlasLocation, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight) {
        float scaleFactor = (float) Minecraft.getInstance().getWindow().getGuiScale();
        float scaledCenterX = ((Minecraft.getInstance().getWindow().getWidth() / scaleFactor) - limits_grapple$CROSSHAIR_TEXTURE_WIDTH) / 2;
//        ((DrawContextFloatDrawTexture)instance).centered_crosshair$drawTexture (Math.round(scaledCenterX + 0.15f));
    };
}
