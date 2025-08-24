package one.devos.nautical.winterisms.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import de.phyrone.brig.wrapper.literal
import de.phyrone.brig.wrapper.runs
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import net.minecraft.server.commands.SaveAllCommand
import java.util.function.Supplier

val ERROR_FAILED = SimpleCommandExceptionType(Component.translatable("commands.save.failed"))

fun restartCommand(dispatcher: CommandDispatcher<CommandSourceStack>) {
    var ticks = 0
    var startCountdown = false
    var didServerSaveCorrectly = false

    dispatcher.literal("restart") {
        require { hasPermission(4) }
        runs {
            it.source.sendSuccess({ Component.literal("Saving server quickly first..").withStyle(ChatFormatting.GREEN) }, true)
            didServerSaveCorrectly = it.source.server.saveEverything(false, false, true)

            if (didServerSaveCorrectly) {
                it.source.sendSuccess({ Component.literal("Saved server!").withStyle(ChatFormatting.GREEN) }, true)
                ticks = 15 * 20

                ServerTickEvents.END_SERVER_TICK.register { server ->
                    ticks--

                    when (ticks) {
                        295 -> it.source.sendSuccess({ Component.literal("Rebooting in 15 seconds")}, true)
                        10 * 20 -> it.source.sendSuccess({ Component.literal("Rebooting in 10 seconds")}, true)
                        5 * 20 -> it.source.sendSuccess({ Component.literal("Rebooting in 5 seconds")}, true)
                        4 * 20 -> it.source.sendSuccess({ Component.literal("Rebooting in 4 seconds")}, true)
                        3 * 20 -> it.source.sendSuccess({ Component.literal("Rebooting in 3 seconds")}, true)
                        2 * 20 -> it.source.sendSuccess({ Component.literal("Rebooting in 2 seconds")}, true)
                        1 * 20 -> it.source.sendSuccess({ Component.literal("Rebooting in 1 seconds")}, true)

                    }
                    if (ticks >= 0) {
                        return@register
                    }

                    it.source.sendSuccess({ Component.literal("Stopping server...")}, true)
                    it.source.server.halt(false)
                }
            } else {
                it.source.sendSuccess({ Component.literal("Server failed to save!").withStyle(ChatFormatting.RED) }, true)
                throw ERROR_FAILED.create()
            }
        }
    }
}
