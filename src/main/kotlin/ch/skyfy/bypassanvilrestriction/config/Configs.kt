package ch.skyfy.bypassanvilrestriction.config

import ch.skyfy.bypassanvilrestriction.BypassAnvilRestrictionMod
import ch.skyfy.tomlconfiglib.ConfigData

object Configs {
    @JvmField
    val MOD_CONFIG = ConfigData.invoke<ModConfig, DefaultModConfig>(BypassAnvilRestrictionMod.CONFIG_DIRECTORY.resolve("config.toml"))
}