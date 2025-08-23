package one.devos.nautical.winterisms.commands

import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import de.phyrone.brig.wrapper.literal
import de.phyrone.brig.wrapper.runs
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import java.util.function.Predicate
import java.util.function.Supplier

fun restartCommand(dispatcher: CommandDispatcher<CommandSourceStack>) {
    dispatcher.literal("restart") {
        require { hasPermission(4) }
        runs {
            it.source.sendSuccess({ Component.literal("Saving server quickly first..").withStyle(ChatFormatting.GREEN) }, true)

            it.source.sendSuccess({ Component.literal("Stopping server...")}, true)
            it.source.server.halt(false)
        }
    }
}