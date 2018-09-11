package com.cintory.hyperion.core.internal

import com.cintory.hyperion.core.PluginSource
import com.cintory.hyperion.plugin.v1.Plugin
import java.util.*

/**
 * Created by Cintory on 2018/8/31 17:13
 * Email：Cintory@gmail.com
 */
class ServiceLoaderPluginSource : PluginSource {
    override fun getPlugins(): Set<Plugin> {
        var plugins = HashSet<Plugin>()
        var loader = ServiceLoader.load(Plugin::class.java)
        loader.forEach { plugins.add(it) }
        return plugins
    }
}