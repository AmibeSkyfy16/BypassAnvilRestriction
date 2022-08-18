package ch.skyfy.bypassanvilrestriction.config

import ch.skyfy.bypassanvilrestriction.config.api.Defaultable
import ch.skyfy.bypassanvilrestriction.config.api.Validatable
import net.peanuuutz.tomlkt.TomlComment

@kotlinx.serialization.Serializable
data class ComplexConfigTest(
    @TomlComment("A nice comment")
    val map1: MutableMap<String, Int>
) : Validatable{

}

class DefaultComplexConfigTest : Defaultable<ComplexConfigTest>{
    override fun getDefault() = ComplexConfigTest(mutableMapOf("test1" to 1, "test2" to 2))

}