package one.devos.nautical.winterisms.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType
import de.phyrone.brig.wrapper.literal
import de.phyrone.brig.wrapper.runs
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket

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
            for (player in it.source.server.playerList.players) {
                player.displayClientMessage(Component.literal("Saving server quickly first..").withStyle(ChatFormatting.GREEN), false)
                didServerSaveCorrectly = it.source.server.saveEverything(false, false, true)

                if (didServerSaveCorrectly) {
                    player.displayClientMessage(Component.literal("Saved server!").withStyle(ChatFormatting.GREEN), false)
                    ticks = 15 * 20
                    lastSource = it.source
                    player.displayClientMessage(15.rebooting(), false)
                } else {
                    player.displayClientMessage(Component.literal("Server failed to save!").withStyle(ChatFormatting.RED), false)
                    throw ERROR_FAILED.create()
                }
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
            for (player in server.playerList.players) {
                player.displayClientMessage(message, false)
                player.connection.send(ClientboundSetTitlesAnimationPacket(0, 20, 0))
                player.connection.send(ClientboundSetTitleTextPacket(message))
            }
        }

        if (ticks >= 0) {
            return@register
        }

        for (player in server.playerList.players) {
            player.displayClientMessage(Component.literal("Restarting server...").withStyle(ChatFormatting.GREEN), false)
        }

        lastSource?.server?.halt(false)
    }
}