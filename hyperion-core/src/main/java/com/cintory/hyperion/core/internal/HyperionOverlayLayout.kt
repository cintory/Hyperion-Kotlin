package com.cintory.hyperion.core.internal

import android.content.Context
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.FrameLayout
import com.cintory.hyperion.core.R
import com.cintory.hyperion.plugin.v1.OnOverlayViewChangedListener
import com.cintory.hyperion.plugin.v1.OverlayContainer

/**
 * Created by Cintory on 2018/8/30 17:36
 * Email：Cintory@gmail.com
 */
class HyperionOverlayLayout(context: Context) : FrameLayout(context), OverlayContainer {

    private val listeners = mutableListOf<OnOverlayViewChangedListener>()

    init {
        id = R.id.hyperion_overlay
        ViewCompat.setImportantForAccessibility(this, ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO)
    }

    override fun setOverlayView(view: View) {
        removeViewIfExists(1)
        addView(view)
        notifyOverlayViewChanged(view)
    }

    override fun setOverlayView(view: Int) {
        removeViewIfExists(1)
        inflate(context, view, this)
        var overlayView = getChildAt(1)
        notifyOverlayViewChanged(overlayView)
    }

    override fun getOverlayView(): View? {
        return if (childCount > 1) getChildAt(1) else null
    }

    override fun removeOverlayView(): Boolean {
        if (childCount > 1) {
            removeViewIfExists(1)
            notifyOverlayViewChanged(null)
            return true
        }
        return false
    }

    override fun addOnOverlayViewChangedListener(listener: OnOverlayViewChangedListener) {
        listeners.add(listener)
    }

    override fun removeOnOverlayViewChangedListener(listener: OnOverlayViewChangedListener): Boolean {
        return listeners.remove(listener)
    }

    private fun notifyOverlayViewChanged(view: View?) {
        listeners.forEach { it.onOverlayViewChanged(view) }
    }

    private fun removeViewIfExists(index: Int) {
        if (childCount > index) {
            removeViewAt(index)
        }
    }
}