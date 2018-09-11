package com.cintory.hyperion.core.internal

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Created by Cintory on 2018/9/3 11:35
 * Email：Cintory@gmail.com
 */
abstract class LifecycleDelegate : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

}