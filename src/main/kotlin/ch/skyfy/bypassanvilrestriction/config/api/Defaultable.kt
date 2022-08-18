package ch.skyfy.bypassanvilrestriction.config.api

interface Defaultable<DATA> {
    fun getDefault(): DATA
}