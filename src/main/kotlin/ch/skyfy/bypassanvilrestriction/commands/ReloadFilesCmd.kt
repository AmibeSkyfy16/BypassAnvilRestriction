package ch.skyfy.bypassanvilrestriction.commands

import ch.skyfy.bypassanvilrestriction.BypassAnvilRestrictionMod
import ch.skyfy.bypassanvilrestriction.config.Configs
import ch.skyfy.tomlconfiglib.ConfigManager
import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.command.CommandSource
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

class ReloadFilesCmd : Command<ServerCommandSource> {

    companion object {
        fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
            val cmd = CommandManager.literal("bypass-anvil-restriction").requires { source -> source.hasPermissionLevel(4) }
                .then(
                    CommandManager.literal("reload").then(
                        CommandManager.argument("fileName", StringArgumentType.string()).suggests(::getConfigFiles).executes(ReloadFilesCmd())
                    )
                )
            dispatcher.register(cmd)
        }

        @Suppress("UNUSED_PARAMETER")
        private fun <S : ServerCommandSource> getConfigFiles(commandContext: CommandContext<S>, suggestionsBuilder: SuggestionsBuilder): CompletableFuture<Suggestions> {
            val list = mutableListOf<String>()
            list.add(Configs.MOD_CONFIG.relativeFilePath.fileName.toString())
            return CommandSource.suggestMatching(list, suggestionsBuilder)
        }
    }

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val fileName = StringArgumentType.getString(context, "fileName")
        val list = mutableListOf<Boolean>()

        when(fileName){
            "config.toml" -> list.add(ConfigManager.reloadConfig(Configs.MOD_CONFIG))
        }

        if (list.contains(false)) {
            context.source.sendFeedback(Supplier { Text.literal("Configuration could not be reloaded") }, false)
            BypassAnvilRestrictionMod.LOGGER.warn("Configuration could not be reloaded")
        } else {
            context.source.sendFeedback(Supplier { Text.literal("The configuration was successfully reloaded") }, false)
            BypassAnvilRestrictionMod.LOGGER.info("The configuration was successfully reloaded")
        }

        return Command.SINGLE_SUCCESS
    }

}