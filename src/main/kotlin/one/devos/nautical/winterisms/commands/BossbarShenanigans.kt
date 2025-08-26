package one.devos.nautical.winterisms.commands

import net.minecraft.resources.ResourceLocation
import net.minecraft.server.bossevents.CustomBossEvent
import one.devos.nautical.winterisms.Winterisms

object BossbarShenanigans {
    val DONT_SLEEP_BOSSBAR_ID = ResourceLocation.fromNamespaceAndPath(Winterisms.MOD_ID, "dont_sleep")
    lateinit var dontSleepBossbar: CustomBossEvent
}