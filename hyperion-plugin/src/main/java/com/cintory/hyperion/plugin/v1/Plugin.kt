package com.cintory.hyperion.plugin.v1

import android.content.Context
import android.os.Build

/**
 * Created by Cintory on 2018/8/29 14:38
 * Email：Cintory@gmail.com
 */

abstract class Plugin {


    private var context: Context? = null
    private var extension: ApplicationExtension? = null

    fun create(context: Context, extension: ApplicationExtension) {
        this.context = context
        this.extension = extension
        onApplicationCreated(context)
    }


    protected open fun onApplicationCreated(context: Context) {

    }

    fun deviceMeetsMinimumApiRequirement(): Boolean {
        return Build.VERSION.SDK_INT >= minimumRequiredApi()
    }

    open fun createPluginModule(): PluginModule? {
        return null
    }

    protected fun getContext(): Context {
        return context!!
    }

    protected fun getApplicationExtension(): ApplicationExtension {
        return extension!!
    }

    /**
     * Define the minum required API level for the plugin. The Hyperion minimum supported API is
     * ICS_MRI (15).
     *
     * @return minimum required api level
     */
    protected fun minimumRequiredApi(): Int {
        return Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
    }

}