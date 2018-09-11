package com.cintory.hyperion.core.internal

import android.app.Activity
import android.content.Context
import android.content.Intent
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/3 14:47
 * Email：Cintory@gmail.com
 */
@AppScope
class HyperionServiceLifecycleDelegate @Inject constructor(private val container: CoreComponentContainer) :
    LifecycleDelegate() {

    private var foregroundActivity: Activity? = null

    override fun onActivityResumed(activity: Activity) {
        foregroundActivity = activity
        val connection = container.getComponent(activity)?.getServiceConnection()
        foregroundActivity!!.bindService(
            Intent(activity, HyperionService::class.java),
            connection,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onActivityPaused(activity: Activity) {
        if (foregroundActivity == activity) {
            val connection = container.getComponent(activity)?.getServiceConnection()
            foregroundActivity!!.unbindService(connection)
            foregroundActivity = null
        }
    }
}