package com.cintory.hyperion.core

import com.cintory.hyperion.plugin.v1.Plugin

/**
 * Created by Cintory on 2018/8/29 14:32
 * Email：Cintory@gmail.com
 */
interface PluginSource {
    fun getPlugins(): Set<Plugin>
}