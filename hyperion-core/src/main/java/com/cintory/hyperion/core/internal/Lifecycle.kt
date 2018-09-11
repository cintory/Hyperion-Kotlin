package com.cintory.hyperion.core.internal

import android.app.Activity
import android.app.Application
import android.os.Bundle
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/3 11:33
 * Email：Cintory@gmail.com
 */
@AppScope
class Lifecycle @Inject constructor(
    @JvmSuppressWildcards
    private val delegates: MutableSet<LifecycleDelegate>
) :
    Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {
        delegates.forEach { it.onActivityPaused(activity!!) }
    }

    override fun onActivityResumed(activity: Activity?) {
        delegates.forEach { it.onActivityResumed(activity!!) }
    }

    override fun onActivityStarted(activity: Activity?) {
        delegates.forEach { it.onActivityStarted(activity!!) }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        delegates.forEach { it.onActivityDestroyed(activity!!) }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        delegates.forEach { it.onActivitySaveInstanceState(activity!!, outState) }
    }

    override fun onActivityStopped(activity: Activity?) {
        delegates.forEach { it.onActivityStopped(activity!!) }
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        delegates.forEach { it.onActivityCreated(activity!!, savedInstanceState) }
    }

}