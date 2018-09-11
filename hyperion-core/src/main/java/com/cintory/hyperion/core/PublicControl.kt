package com.cintory.hyperion.core

import android.app.Activity

/**
 * Created by Cintory on 2018/8/29 16:48
 * Email：Cintory@gmail.com
 */
interface PublicControl {

    fun open()

    fun open(activity: Activity)

    fun setShakeGestureSensitivity(sensitivity: Float)

    fun getShakeGestureSensitivity(): Float

    fun setPluginSource(pluginSource: PluginSource)

    fun getPluginSource(): PluginSource
}