package com.cintory.hyperion.core.internal

import android.app.Activity
import android.util.Log
import com.cintory.hyperion.core.PluginSource
import com.cintory.hyperion.core.PublicControl
import com.cintory.hyperion.core.R
import com.cintory.hyperion.plugin.v1.ForegroundManager
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/4 15:48
 * Email：Cintory@gmail.com
 */
@AppScope
class PublicControlImpl @Inject constructor(
    private val container: CoreComponentContainer, private
    val foregroundManager: ForegroundManager
) : PublicControl {
    override fun open() {
        val foregroundActivity = foregroundManager.getForegroundActivity()
        if (foregroundActivity != null)
            open(foregroundActivity)
    }

    override fun open(activity: Activity) {
        val menu = activity.findViewById<HyperionMenuLayout>(R.id.hyperion_menu)
        if (menu == null) {
            Log.d("Hyperion", "Could not find Hyperion menu in this activity.")
            return
        }
        menu.expand()
    }

    override fun setShakeGestureSensitivity(sensitivity: Float) {
        this.sensitivity = sensitivity
        container.activities.forEach {
            val menu = it.findViewById<HyperionMenuLayout>(R.id.hyperion_menu)
            menu.setShakeGestureSensitivity(sensitivity)
        }
    }

    override fun getShakeGestureSensitivity(): Float {
        return sensitivity
    }

    override fun setPluginSource(pluginSource: PluginSource) {
        container.pluginSource = pluginSource
    }

    override fun getPluginSource(): PluginSource {
        return container.pluginSource
    }

    private var sensitivity = 3.0f


}