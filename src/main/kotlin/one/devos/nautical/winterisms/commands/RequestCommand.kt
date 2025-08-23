package one.devos.nautical.winterisms.commands

import com.mojang.brigadier.CommandDispatcher
import de.phyrone.brig.wrapper.literal
import de.phyrone.brig.wrapper.runs
import net.minecraft.ChatFormatting
import net.minecraft.commands.CommandSourceStack
import net.minecraft.network.chat.Component
import net.minecraft.network.protocol.game.ClientboundSetSubtitleTextPacket
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket

fun requestCommand(dispatcher: CommandDispatcher<CommandSourceStack>) {
    dispatcher.literal("request") {
        literal("dontSleep") {
            runs {
                for (player in it.source.server.playerList.players) {
                    player.connection.send(ClientboundSetTitlesAnimationPacket(10, 200, 10))

                    player.connection.send(
                        ClientboundSetTitleTextPacket(
                            Component.literal("Hey! Please do not sleep!").withStyle(
                                ChatFormatting.RED
                            )
                        )
                    )
                    player.connection.send(
                        ClientboundSetSubtitleTextPacket(
                            Component.literal("Requested by ").append(it.source.playerOrException.name).withStyle(
                                ChatFormatting.GOLD
                            )
                        )
                    )

                    player.displayClientMessage(
                        Component.literal(it.source.playerOrException.name.string)
                            .append(" has requested that players do not sleep!"), false
                    )
                }
            }
        }

        literal("attention") {
            runs {
                for (player in it.source.server.playerList.players) {
                    player.connection.send(ClientboundSetTitlesAnimationPacket(10, 200, 10))

                    player.connection.send(
                        ClientboundSetTitleTextPacket(
                            Component.literal("Hey! Look in chat!").withStyle(
                                ChatFormatting.RED
                            )
                        )
                    )
                    player.connection.send(
                        ClientboundSetSubtitleTextPacket(
                            Component.literal("Requested by ").append(it.source.playerOrException.name).withStyle(
                                ChatFormatting.GOLD
                            )
                        )
                    )

                    player.displayClientMessage(
                        Component.literal(it.source.playerOrException.name.string)
                            .append(" has requested that players read the chat above!"), false
                    )
                }
            }
        }

        // i hate this, i feel it should be in the require block with the has permission nested as well but no that's
        // not how it fuckin works apparently what the fuck is this terrible implication
        require { hasPermission(4) }
        literal("serverRestart") {
            runs {
                for (player in it.source.server.playerList.players) {
                    player.connection.send(ClientboundSetTitlesAnimationPacket(10, 200, 10))

                    player.connection.send(
                        ClientboundSetTitleTextPacket(
                            Component.literal("Server Restart requested!").withStyle(
                                ChatFormatting.RED
                            )
                        )
                    )
                    player.connection.send(
                        ClientboundSetSubtitleTextPacket(
                            Component.literal("Prepare for server restart, if everyone agrees.").append(it.source.playerOrException.name).withStyle(
                                ChatFormatting.GOLD
                            )
                        )
                    )

                    player.displayClientMessage(
                        Component.literal("An admin has requested for a server restart! Please prepare, if everyone agrees."), false
                    )
                }
            }
        }
    }
}