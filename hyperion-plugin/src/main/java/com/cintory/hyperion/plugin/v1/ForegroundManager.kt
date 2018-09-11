package com.cintory.hyperion.plugin.v1

import android.app.Activity

/**
 * Created by Cintory on 2018/8/29 14:44
 * Email：Cintory@gmail.com
 */

interface ForegroundManager {

    fun getForegroundActivity(): Activity?
    fun isApplicationForeground(): Boolean

    fun addOnApplicationForegroundListener(listener: OnApplicationForegroundListener)
    fun removeOnApplicationForegroundListener(listener: OnApplicationForegroundListener): Boolean

    fun addOnApplicationBackgroundListener(listener: OnApplicationBackgroundListener)
    fun removeOnApplicationBackgroundListener(listener: OnApplicationBackgroundListener): Boolean
}