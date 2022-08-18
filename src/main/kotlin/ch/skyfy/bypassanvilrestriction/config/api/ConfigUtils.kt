package ch.skyfy.bypassanvilrestriction.config.api

object ConfigUtils {
    fun loadClassesByReflection(classesToLoad: Array<Class<*>>) {
        for (config in classesToLoad) {
            val canonicalName = config.canonicalName
            try {
                Class.forName(canonicalName)
            } catch (e: ClassNotFoundException) {
                throw RuntimeException(e)
            }
        }
    }
}