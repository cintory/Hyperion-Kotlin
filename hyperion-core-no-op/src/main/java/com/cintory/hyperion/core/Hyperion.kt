package com.cintory.hyperion.core

import android.app.Activity
import android.view.View

/**
 * Created by Cintory on 2018/9/7 10:55
 * Email：Cintory@gmail.com
 */
class Hyperion {

    companion object {

        fun createPluginView(activity: Activity): View? {
            //  no-op
            return null
        }

        fun getShakeGestureSensitivity(): Float {
            return Float.MIN_VALUE
        }

        fun setShakeGestureSensitivity(sensitivity: Float) {}

        fun open() {
            // no-op
        }

        fun open(activity: Activity) {
            // no-op
        }

        fun setPluginSource(vararg pluginSource: Any) {
            // no-op
        }
    }
}