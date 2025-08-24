package one.devos.nautical.winterisms.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import de.phyrone.brig.wrapper.literal
import de.phyrone.brig.wrapper.runs
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSource
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component

val ERROR_FAILED = SimpleCommandExceptionType(Component.translatable("commands.save.failed"))

val REBOOT_TEMPLATE = "Rebooting in %s second(s)"

private fun Int.rebooting(): Component {
    return Component.literal(REBOOT_TEMPLATE.format(this.toString()))
}

fun restartCommand(dispatcher: CommandDispatcher<CommandSourceStack>) {
    var ticks = 0
    var startCountdown = false
    var didServerSaveCorrectly = false
    var lastSource: CommandSourceStack? = null

    dispatcher.literal("restart") {
        require { hasPermission(4) }
        runs {
            it.source.sendSuccess({ Component.literal("Saving server quickly first..").withStyle(ChatFormatting.GREEN) }, true)
            didServerSaveCorrectly = it.source.server.saveEverything(false, false, true)

            if (didServerSaveCorrectly) {
                it.source.sendSuccess({ Component.literal("Saved server!").withStyle(ChatFormatting.GREEN) }, true)
                ticks = 15 * 20
                lastSource = it.source
                it.source.sendSuccess({ 15.rebooting() }, true)
            } else {
                it.source.sendSuccess({ Component.literal("Server failed to save!").withStyle(ChatFormatting.RED) }, true)
                throw ERROR_FAILED.create()
            }
        }
    }

    ServerTickEvents.END_SERVER_TICK.register { server ->
        ticks--

        val message = when (ticks) {
            10 * 20 -> 10.rebooting()
            5 * 20 -> 5.rebooting()
            4 * 20 -> 4.rebooting()
            3 * 20 -> 3.rebooting()
            2 * 20 -> 2.rebooting()
            1 * 20 -> 1.rebooting()
            else -> null
        }

        if (message != null) {
            lastSource?.sendSuccess({ message }, true)
        }

        if (ticks >= 0) {
            return@register
        }

        lastSource?.sendSuccess({ Component.literal("Stopping server...")}, true)
        lastSource?.server?.halt(false)
    }
}