package ch.skyfy.bypassanvilrestriction.config

import ch.skyfy.bypassanvilrestriction.BypassAnvilRestrictionMod.Companion.MOD_ID
import ch.skyfy.tomlconfiglib.Defaultable
import ch.skyfy.tomlconfiglib.Validatable
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.TomlComment

@Serializable
data class ModConfig(
    @TomlComment("[Default -> 1000] The new level limit. 40 by default in Minecraft vanilla")
    @JvmField
    val levelLimit: Int,

    @TomlComment(
        """
        [Default -> 0] In Minecraft vanilla, each time you combine or repair an item, the next cost will be increased. See https://minecraft.fandom.com/wiki/Anvil_mechanics
        Here, you can nerf the cost by applying a percentage of reduction. 100 % mean that repair cost will always cost 0
        
    """,
    )
    @JvmField
    val nerfIncrementalCostByPercent: Int,
) : Validatable {
    override fun validateImpl(errors: MutableList<String>) {
        errors.add("[ERRRROR] TEST")
        if (nerfIncrementalCostByPercent < 0 || nerfIncrementalCostByPercent > 100)
            errors.add("[ERROR] $MOD_ID -> config.toml -> nerfIncrementalCostByPercent must contains a value between 0 and 100 (inclusive) ! Current value is $nerfIncrementalCostByPercent")
    }
}

class DefaultModConfig : Defaultable<ModConfig> {
    override fun getDefault() = ModConfig(1000, 0)
}