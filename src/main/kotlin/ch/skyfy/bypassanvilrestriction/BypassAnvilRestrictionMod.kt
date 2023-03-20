package ch.skyfy.bypassanvilrestriction

import ch.skyfy.bypassanvilrestriction.commands.ReloadFilesCmd
import ch.skyfy.bypassanvilrestriction.config.Configs
import ch.skyfy.bypassanvilrestriction.utils.setupConfigDirectory
import ch.skyfy.tomlconfiglib.ConfigManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
class BypassAnvilRestrictionMod : ModInitializer {

    companion object {
        const val MOD_ID: String = "bypass_anvil_restriction"
        val CONFIG_DIRECTORY: Path = FabricLoader.getInstance().configDir.resolve(MOD_ID)
        val LOGGER: Logger = LogManager.getLogger(BypassAnvilRestrictionMod::class.java)
    }

    init {
        setupConfigDirectory()
        ConfigManager.loadConfigs(arrayOf(Configs.javaClass))
    }

    override fun onInitialize() { registerCommands() }

    private fun registerCommands() = CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
        ReloadFilesCmd.register(dispatcher)
    }

}