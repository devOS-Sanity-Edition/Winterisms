package one.devos.nautical.winterisms.crash

import net.fabricmc.loader.api.FabricLoader
import one.devos.nautical.winterisms.Winterisms
import java.io.File
import java.nio.file.Path
import java.util.*
import java.util.function.Consumer
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

object RoombaCrash {
    val soundFile: Path = FabricLoader.getInstance().configDir.resolve("crash.wav")
    var clip: Clip? = null

    fun init() {
        getSound().ifPresent(Consumer { it: File? ->
            try {
                val audioInputStream: AudioInputStream = AudioSystem.getAudioInputStream(it)
                clip = AudioSystem.getClip()
                clip?.open(audioInputStream)

                if (clip!!.isOpen) {
                    Winterisms.LOGGER.info("[Winterisms] Roomba armed?")
                }
            } catch (exception: Exception) {
                Winterisms.LOGGER.error("[Winterisms] Failed to load audio $soundFile: $exception")
            }
        })
    }

    fun playRoombaFallingDownTheStairs() {
        if (clip != null) {
            clip?.start()
            try {
                Thread.sleep(clip!!.microsecondLength / 1000)
            } catch (e: Exception) {
                Winterisms.LOGGER.error("[Winterisms] Hm, couldn't play crash sound. ${e.message}")
            }
        }
    }

    fun getSound(): Optional<File> {
        try {
            return Optional.of(soundFile.toFile().absoluteFile)
        } catch (exception: Exception) {
            return Optional.empty()
        }
    }
}