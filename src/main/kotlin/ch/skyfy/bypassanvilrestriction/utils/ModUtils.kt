package ch.skyfy.bypassanvilrestriction.utils

import ch.skyfy.bypassanvilrestriction.BypassAnvilRestrictionMod
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

fun setupConfigDirectory(){
    try {
        if(!BypassAnvilRestrictionMod.CONFIG_DIRECTORY.exists()) BypassAnvilRestrictionMod.CONFIG_DIRECTORY.createDirectory()
    } catch (e: java.lang.Exception) {
        BypassAnvilRestrictionMod.LOGGER.fatal("An exception occurred. Could not create the root folder that should contain the configuration files")
        throw RuntimeException(e)
    }
}