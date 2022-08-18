package ch.skyfy.bypassanvilrestriction.config.api

import java.nio.file.Path

data class ConfigData<DATA : Validatable>(@JvmField var `data`: DATA, val relativeFilePath: Path) {
    companion object {
        inline operator fun <reified DATA : Validatable, reified DEFAULT : Defaultable<DATA>> invoke(relativeFilePath: Path): ConfigData<DATA> =
            ConfigData(ConfigManager.getOrCreateConfig<DATA, DEFAULT>(relativeFilePath), relativeFilePath)

        inline operator fun <reified DATA : Validatable> invoke(relativeFilePath: Path, defaultFile: String): ConfigData<DATA> =
            ConfigData(ConfigManager.getOrCreateConfig(relativeFilePath, defaultFile), relativeFilePath)
    }
}
