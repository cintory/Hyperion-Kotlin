package com.cintory.hyperion.core.internal

import android.app.Activity
import android.view.ViewGroup
import com.cintory.hyperion.core.R
import com.cintory.hyperion.plugin.v1.*

/**
 * Created by Cintory on 2018/8/30 17:23
 * Email：Cintory@gmail.com
 */
class PluginExtensionImpl(private val component: CoreComponent) : PluginExtension {

    private var menu: HyperionMenu? = null

    override fun getActivity(): Activity {
        return component.getActivity()
    }

    override fun getContentRoot(): ViewGroup {
        val overlayLayout = getActivity().findViewById<HyperionOverlayLayout>(R.id.hyperion_overlay)
        return overlayLayout.getChildAt(0) as ViewGroup

    }

    override fun getOverlayContainer(): OverlayContainer {
        return component.getOverlayContainer()
    }

    override fun getActivityResults(): ActivityResults {
        return component.getActivityResults()
    }

    override fun getAttributeTranslator(): AttributeTranslator {
        return component.getAttributeTranslator()
    }

    override fun getMeasurementHelper(): MeasurementHelper {
        return component.getMeasurementHelper()
    }

    override fun getHyperionMenu(): HyperionMenu? {
        return menu
    }

    fun setHyperionMenu(menu: HyperionMenu?) {
        this.menu = menu
    }

}