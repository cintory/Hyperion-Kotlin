package com.cintory.hyperion.core.internal

import com.cintory.hyperion.core.PluginSource
import com.cintory.hyperion.plugin.v1.Plugin
import javax.inject.Inject
import javax.inject.Provider
import kotlin.collections.HashSet

/**
 * Created by Cintory on 2018/8/29 17:16
 * Email：Cintory@gmail.com
 */
@AppScope
class PluginRepository @Inject constructor(private val pluginSource: PluginSource) {

    private val plugins = Cached(PluginsProvider())

    fun getPlugins(): Plugins {
        return plugins.get()
    }

    private inner class PluginsProvider : Provider<Plugins> {
        override fun get(): Plugins {
            val plugins = pluginSource.getPlugins()
            var compatiblePlugins = HashSet<Plugin>()
            plugins.forEach {
                if (it.deviceMeetsMinimumApiRequirement()) {
                    compatiblePlugins.add(it)
                }
            }
            return Plugins(compatiblePlugins)
        }
    }
}