package ch.skyfy.bypassanvilrestriction.config.api

object ConfigLoader {

    fun loadConfigs(classesToLoad: Array<Class<*>>) = ConfigUtils.loadClassesByReflection(classesToLoad)

    inline fun <reified DATA : Validatable> reloadConfig(configData: ConfigData<DATA>) : Boolean {
        try {
            configData.data = ConfigManager.get(configData.relativeFilePath, shouldCrash = false)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

}