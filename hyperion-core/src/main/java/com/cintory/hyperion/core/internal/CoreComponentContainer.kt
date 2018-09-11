package com.cintory.hyperion.core.internal

import android.app.Activity
import android.support.v4.util.ArrayMap
import com.cintory.hyperion.core.PluginSource
import javax.inject.Inject

/**
 * Created by Cintory on 2018/8/30 11:24
 * Email：Cintory@gmail.com
 */
@AppScope
 class CoreComponentContainer @Inject
constructor() {

    private val components = ArrayMap<Activity, CoreComponent>()
    var pluginSource: PluginSource

    val activities: Set<Activity>
        get() = components.keys

    init {
        pluginSource = ServiceLoaderPluginSource()
    }

    fun getComponent(activity: Activity): CoreComponent? {
        return components[activity]
    }

    fun removeComponent(activity: Activity): CoreComponent? {
        return components.remove(activity)
    }

    fun putComponent(activity: Activity, component: CoreComponent): CoreComponent? {
        return components.put(activity, component)
    }

}