package one.devos.nautical.winterisms.mixin.client;

import net.minecraft.client.Minecraft;
import one.devos.nautical.winterisms.client.titlebar.DarkModeTitleBar;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class WindowTitlebarMixin {
    @Inject(method = "setWindowActive", at = @At("HEAD"))
    private void titlebar(boolean windowActive, CallbackInfo ci) {
        long window = Minecraft.getInstance().getWindow().getWindow();
        int windowId = (int) GLFWNativeWin32.glfwGetWin32Window(window);

        DarkModeTitleBar.INSTANCE.darkModeTitleBarForWindows11(windowId);
    }
}
