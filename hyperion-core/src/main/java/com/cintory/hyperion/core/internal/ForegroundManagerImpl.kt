package com.cintory.hyperion.core.internal

import android.app.Activity
import com.cintory.hyperion.plugin.v1.ForegroundManager
import com.cintory.hyperion.plugin.v1.OnApplicationBackgroundListener
import com.cintory.hyperion.plugin.v1.OnApplicationForegroundListener
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/4 13:56
 * Email：Cintory@gmail.com
 */
@AppScope
class ForegroundManagerImpl @Inject constructor() : ForegroundManager {

    private val foregroundListeners = ArrayList<OnApplicationForegroundListener>()
    private val backgroundListeners = ArrayList<OnApplicationBackgroundListener>()
    private var activity: Activity? = null
    private var appForeground = false

    override fun getForegroundActivity(): Activity {
        return activity ?: throw AssertionError("No foreground Activity.")
    }

    fun setForegroundActivity(activity: Activity?) {
        this.activity = activity
    }

    override fun isApplicationForeground(): Boolean {
        return appForeground
    }

    fun setApplicationForeground(appForeground: Boolean) {
        this.appForeground = appForeground
    }

    override fun addOnApplicationForegroundListener(listener: OnApplicationForegroundListener) {
        foregroundListeners.add(listener)
    }

    override fun removeOnApplicationForegroundListener(listener: OnApplicationForegroundListener): Boolean {
        return foregroundListeners.remove(listener)
    }

    override fun addOnApplicationBackgroundListener(listener: OnApplicationBackgroundListener) {
        backgroundListeners.add(listener)
    }

    override fun removeOnApplicationBackgroundListener(listener: OnApplicationBackgroundListener): Boolean {
        return backgroundListeners.remove(listener)
    }

    fun notifyAppForegrounded(activity: Activity) {
        foregroundListeners.forEach { it.onApplicationForeground(activity.applicationContext) }
    }

    fun notifyAppBackgrounded(activity: Activity) {
        backgroundListeners.forEach { it.onApplicationBackground(activity.applicationContext) }
    }
}