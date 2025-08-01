package one.devos.nautical.winterssummerfixes.mixin.compat.client.xaeros_minimap;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import net.minecraft.network.chat.Component;
import one.devos.nautical.winterssummerfixes.utils.ConversionKt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xaero.common.events.ClientEvents;

@IfModLoaded("xaerominimap")
@Mixin(ClientEvents.class)
public abstract class ClientEventsMixin {
    @WrapOperation(method = "handleChatMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;getString()Ljava/lang/String;"))
    private String tryConvertToXaerosFormat(Component instance, Operation<String> original) {
        var string = original.call(instance);

        // JM format: [x:0,z:0]
        if (string.contains("[") && string.contains("]") && string.contains(",")) {
            return ConversionKt.convertJourneyMapToXaeros(string);
        }

        return string;
    }
}
