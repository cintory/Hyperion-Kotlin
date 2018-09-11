package com.cintory.hyperion.core.internal

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.util.SimpleArrayMap
import com.cintory.hyperion.plugin.v1.HyperionIgnore
import javax.inject.Inject

@AppScope
class HyperionIgnoreFilter @Inject constructor(private val lifecycle: Lifecycle) :
    Application.ActivityLifecycleCallbacks {

    private val cache = SimpleArrayMap<Class<*>, Boolean>(5)

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (!shouldIgnore(activity)) {
            lifecycle.onActivityCreated(activity, savedInstanceState)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        if (!shouldIgnore(activity)) {
            lifecycle.onActivityStarted(activity)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        if (!shouldIgnore(activity)) {
            lifecycle.onActivityResumed(activity)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (!shouldIgnore(activity)) {
            lifecycle.onActivityPaused(activity)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        if (!shouldIgnore(activity)) {
            lifecycle.onActivityStopped(activity)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        if (!shouldIgnore(activity)) {
            lifecycle.onActivitySaveInstanceState(activity, outState)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (!shouldIgnore(activity)) {
            lifecycle.onActivityDestroyed(activity)
        }
    }

    private fun shouldIgnore(activity: Activity): Boolean {
        val clz = activity.javaClass
        return if (cache.containsKey(clz)) {
            cache.get(clz) ?: false
        } else {
            val ignore = activity.javaClass.getAnnotation(HyperionIgnore::class.java)
            val shouldIgnore = ignore != null
            cache.put(clz, shouldIgnore)
            shouldIgnore
        }
    }
}