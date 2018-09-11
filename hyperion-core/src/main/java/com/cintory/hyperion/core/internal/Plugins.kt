package com.cintory.hyperion.core.internal

import com.cintory.hyperion.plugin.v1.Plugin
import com.cintory.hyperion.plugin.v1.PluginModule
import java.util.*
import kotlin.collections.HashSet

/**
 * Created by Cintory on 2018/8/29 17:57
 * Email：Cintory@gmail.com
 */
class Plugins(private val plugins: Set<Plugin>) {

    fun get(): Set<Plugin> {
        return Collections.unmodifiableSet(plugins)
    }

    fun createModules(): Set<PluginModule> {
        val modules = HashSet<PluginModule>()
        plugins.forEach {
            val module = it.createPluginModule()
            if (module != null) {
                modules.add(module)
            }
        }
        return modules
    }

}