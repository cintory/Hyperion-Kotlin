package com.cintory.hyperion.plugin.v1

import android.app.Activity
import android.view.ViewGroup

/**
 * Created by Cintory on 2018/8/29 15:02
 * Email：Cintory@gmail.com
 */
interface PluginExtension {

    fun getActivity(): Activity

    fun getContentRoot(): ViewGroup

    fun getOverlayContainer(): OverlayContainer

    fun getActivityResults(): ActivityResults

    fun getAttributeTranslator(): AttributeTranslator

    fun getMeasurementHelper(): MeasurementHelper

    fun getHyperionMenu(): HyperionMenu?
}