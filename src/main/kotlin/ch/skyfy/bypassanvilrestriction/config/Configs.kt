package ch.skyfy.bypassanvilrestriction.config

import ch.skyfy.bypassanvilrestriction.BypassAnvilRestrictionMod
import ch.skyfy.bypassanvilrestriction.config.api.ConfigData


object Configs {
    @JvmField
    val MOD_CONFIG = ConfigData.invoke<ModConfig, DefaultModConfig>(BypassAnvilRestrictionMod.CONFIG_DIRECTORY.resolve("config.toml"))
    val CONFIG_TEST = ConfigData.invoke<ComplexConfigTest, DefaultComplexConfigTest>(BypassAnvilRestrictionMod.CONFIG_DIRECTORY.resolve("configTest.toml"))
}