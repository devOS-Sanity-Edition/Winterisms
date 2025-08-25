package one.devos.nautical.winterisms.mixin.common;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.CrashReport;
import net.minecraft.ReportType;
import one.devos.nautical.winterisms.crash.CrashHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;
import java.util.List;

/**
 * Originally written by Maximumpower55, as a part of the TeaBridge project
 * <p>
 * src: <a href="https://github.com/devOS-Sanity-Edition/TeaBridge/blob/main/src/main/java/one/devos/nautical/teabridge/mixin/CrashReportMixin.java">...</a>
 * <p>
 * License: MIT
 */
@Mixin(CrashReport.class)
public class CrashReportMCLogsMixin {
    @Inject(method = "saveToFile(Ljava/nio/file/Path;Lnet/minecraft/ReportType;Ljava/util/List;)Z", at = @At("HEAD"))
    private void crashed(Path path, ReportType type, List<String> links, CallbackInfoReturnable<Boolean> cir) {
        CrashHandler.INSTANCE.oops((CrashReport) (Object) this);
    }

    @Inject(method = "getFriendlyReport*", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;", ordinal = 0))
    private void uh(CallbackInfoReturnable<String> cir, @Local StringBuilder stringBuilder) {
        CrashHandler.INSTANCE.uhString(stringBuilder);
    }
}
