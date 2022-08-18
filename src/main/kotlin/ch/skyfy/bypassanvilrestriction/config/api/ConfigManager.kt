package ch.skyfy.bypassanvilrestriction.config.api

import ch.skyfy.bypassanvilrestriction.BypassAnvilRestrictionMod.Companion.LOGGER
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import net.peanuuutz.tomlkt.Toml
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.*
import kotlin.reflect.full.createInstance

@Suppress("unused")
object ConfigManager {

    var toml = Toml {
        ignoreUnknownKeys = false
    }

    /**
     *
     * This method try to deserialize a toml file to an object (DATA).
     * If no toml file is present, a new object (DATA) will be created by a class that implement Defaultable<DATA>
     * Also a new toml file will be created based on the DATA object
     *
     * If the json file does not match the json standard, an error will be thrown and the program will stop
     */


    /**
     *
     * This method try to deserialize a toml file to an object (DATA).
     * If no toml file is present, a new object (DATA) will be created by a class that implement Defaultable<DATA>
     * Also a new toml file will be created based on the DATA object
     *
     * If the json file does not match the json standard, an error will be thrown and the program will stop
     */
    inline fun <reified DATA : Validatable, reified DEFAULT : Defaultable<DATA>> getOrCreateConfig(
        file: Path,
        toml: Toml = this.toml,
    ): DATA {
        try {
            val d: DATA = if (file.exists()) get(file, toml, true)
            else save(DEFAULT::class.createInstance().getDefault(), file, toml)
            d.confirmValidate(mutableListOf(), true)
            return d
        } catch (e: java.lang.Exception) {
            throw RuntimeException(e)
        }
    }


    /**
     * This method try to deserialize a toml file to a kotlin object (DATA).
     * But this time, if the json file is not present, the kotlin object (DATA)
     * will be created from another json file (a default json, stored inside the jar (in resources folder))
     *
     * If the json file does not match the json standard, an error will be thrown and the program will stop
     */
    inline fun <reified DATA : Validatable> getOrCreateConfig(
        file: Path,
        defaultFile: String,
        toml: Toml = this.toml,
    ): DATA {
        try {
            return if (file.exists()) get(file, toml, true)
            else get(extractResource(file, defaultFile, DATA::class.java.classLoader), toml, true)
        } catch (e: java.lang.Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     * Try to convert a toml data stored in a file to an object
     *
     */
    @Throws(Exception::class)
    inline fun <reified DATA : Validatable> get(file: Path, toml: Toml = this.toml, shouldCrash: Boolean): DATA {
        val `data`: DATA = toml.decodeFromString(file.readText())
        if (!`data`.confirmValidate(mutableListOf(), shouldCrash)) throw Exception("The json file is not valid !!!")
        return `data`
    }

    /**
     * Use in getOrCreateConfig fun
     */
    @Throws(Exception::class)
    inline fun <reified DATA : Validatable> save(
        config: DATA,
        file: Path,
        toml: Toml = this.toml,
    ): DATA {
        config.confirmValidate(mutableListOf(), true)
        file.parent.createDirectories()
        file.createFile()
        file.writeText(toml.encodeToString(config))
        return config
    }

    /**
     * Use by coder to save edited data.
     * For example, you add a user in a list, you have the called this method to save it to the json file
     *
     */
    @Throws(Exception::class)
    inline fun <reified DATA : Validatable> save(
        configData: ConfigData<DATA>,
        toml: Toml = this.toml
    ) {
        if (!configData.data.confirmValidate(mutableListOf(), false)) {
            LOGGER.warn("The data you tried to save has not been saved, because something is not valid")
            return
        }
        configData.relativeFilePath.writeText(toml.encodeToString(configData.data))
    }

    fun extractResource(file: Path, resource: String, classLoader: ClassLoader): Path {
        val s = Objects.requireNonNull(classLoader.getResourceAsStream(resource))
        Files.copy(s, file)
        return file
    }

}