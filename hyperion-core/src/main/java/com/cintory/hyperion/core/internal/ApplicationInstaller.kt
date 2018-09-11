package com.cintory.hyperion.core.internal

import android.app.Application
import android.support.annotation.MainThread
import com.cintory.hyperion.plugin.v1.ApplicationExtension
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/3 15:14
 * Email：Cintory@gmail.com
 */
class ApplicationInstaller @Inject constructor(
    private val pluginRepository: PluginRepository,
    private val application: Application,
    val applicationExtension: ApplicationExtension
) {
    private var applicationCreated = false

    @MainThread
    fun installIfNeed() {
        if (!applicationCreated) {
            val plugins = pluginRepository.getPlugins()
            plugins.get().forEach { it.create(application, applicationExtension) }
            applicationCreated = true
        }
    }

}