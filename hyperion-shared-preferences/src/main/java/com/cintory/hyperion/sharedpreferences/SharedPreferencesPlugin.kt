package com.cintory.hyperion.sharedpreferences

import com.cintory.hyperion.plugin.v1.Plugin
import com.cintory.hyperion.plugin.v1.PluginModule
import com.google.auto.service.AutoService

/**
 * Created by Cintory on 2018/9/7 11:16
 * Email：Cintory@gmail.com
 */

@AutoService(Plugin::class)
class SharedPreferencesPlugin : Plugin() {
    override fun createPluginModule(): PluginModule {
        return SharedPreferencesPluginModule()
    }
}