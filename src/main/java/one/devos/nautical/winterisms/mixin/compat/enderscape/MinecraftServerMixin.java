package one.devos.nautical.winterisms.mixin.compat.enderscape;

import com.bawnorton.mixinsquared.TargetHandler;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.dimension.LevelStem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@IfModLoaded("enderscape")
@Mixin(value = MinecraftServer.class, priority = 1050)
public abstract class MinecraftServerMixin {
    @Shadow
    public abstract RegistryAccess.Frozen registryAccess();

    @TargetHandler(mixin = "net.bunten.enderscape.mixin.MinecraftServerMixin", name = "addSurfaceRules", prefix = "handler")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    private void avoidEndStemCrash(CallbackInfo ci) {
        if (this.registryAccess().lookup(Registries.LEVEL_STEM).isEmpty())
            ci.cancel();

        if (this.registryAccess().lookupOrThrow(Registries.LEVEL_STEM).get(LevelStem.END).isEmpty())
            ci.cancel();
    }
}
