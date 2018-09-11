package com.cintory.hyperion.core.internal

import android.app.Activity
import android.os.Bundle
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/4 13:54
 * Email：Cintory@gmail.com
 */
@AppScope
class ForegroundManagerLifecycleDelegate @Inject constructor(
    private val foregroundManager: ForegroundManagerImpl
) : LifecycleDelegate() {

    private var activityCount = 0

    override fun onActivityStarted(activity: Activity) {
        if (!appIsForegrounded()) {
            foregroundManager.notifyAppBackgrounded(activity)
            activityCount = 0
        }
        activityCount++
        foregroundManager.setApplicationForeground(appIsForegrounded())
    }

    override fun onActivityResumed(activity: Activity) {
        foregroundManager.setForegroundActivity(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        val foregroundActivity = foregroundManager.getForegroundActivity()
        if (foregroundActivity == activity) {
            foregroundManager.setForegroundActivity(null)
        }
    }

    override fun onActivityStopped(activity: Activity) {
        activityCount--
        if (!appIsForegrounded()) {
            foregroundManager.notifyAppBackgrounded(activity)
            activityCount = 0
        }
        foregroundManager.setApplicationForeground(appIsForegrounded())
    }


    private fun appIsForegrounded(): Boolean {
        return activityCount > 0
    }
}