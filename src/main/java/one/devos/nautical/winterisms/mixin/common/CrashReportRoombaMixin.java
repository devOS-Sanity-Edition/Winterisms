package one.devos.nautical.winterisms.mixin.common;

import net.minecraft.CrashReport;
import one.devos.nautical.winterisms.crash.RoombaCrash;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Mixin(CrashReport.class)
//public class CrashReportRoombaMixin {
//    @Inject(method = "forThrowable", at = @At("HEAD"))
//    private static void tossTheRoomba(Throwable cause, String description, CallbackInfoReturnable<CrashReport> cir) {
//        RoombaCrash.INSTANCE.playRoombaFallingDownTheStairs();
        // we're doing it in the CrashReportMCLogsMixin now bc i couldnt get this to work :1
//    }
//}
