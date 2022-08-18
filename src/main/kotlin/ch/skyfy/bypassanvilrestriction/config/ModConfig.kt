package ch.skyfy.bypassanvilrestriction.config

import ch.skyfy.bypassanvilrestriction.config.api.Defaultable
import ch.skyfy.bypassanvilrestriction.config.api.Validatable
import com.akuleshov7.ktoml.annotations.TomlComments
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.TomlComment

@Serializable
data class ModConfig(
    @TomlComment("The new level limit. 40 by default in Minecraft vanilla")
    @JvmField
    val levelLimit: Int
) : Validatable {

}

class DefaultModConfig : Defaultable<ModConfig>{
    override fun getDefault() = ModConfig(100)
}