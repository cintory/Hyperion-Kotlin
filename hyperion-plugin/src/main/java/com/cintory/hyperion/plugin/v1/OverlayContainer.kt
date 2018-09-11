package com.cintory.hyperion.plugin.v1

import android.support.annotation.LayoutRes
import android.view.View

/**
 * Created by Cintory on 2018/8/29 15:06
 * Email：Cintory@gmail.com
 */
interface OverlayContainer {

    fun setOverlayView(view: View)

    fun setOverlayView(@LayoutRes view: Int)

    fun getOverlayView(): View?

    fun removeOverlayView(): Boolean

    fun addOnOverlayViewChangedListener(listener: OnOverlayViewChangedListener)

    fun removeOnOverlayViewChangedListener(listener: OnOverlayViewChangedListener): Boolean

}