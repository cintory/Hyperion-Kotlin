package com.cintory.hyperion.core.internal

import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import com.cintory.hyperion.plugin.v1.MeasurementHelper
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/3 10:18
 * Email：Cintory@gmail.com
 */
class MeasurementHelperImpl @Inject constructor(private val displayMetrics: DisplayMetrics) :
    MeasurementHelper {


    override fun getParentRelativeRect(view: View, rect: Rect) {
        rect.left = getRelativeLeft(view)
        rect.top = getRelativeTop(view)
        rect.right = getRelativeRight(view)
        rect.bottom = getRelativeBottom(view)
    }

    override fun getRelativeLeft(view: View): Int {
        return if (view.parent == view.rootView) {
            view.left
        } else {
            view.left + getRelativeLeft(view.parent as View)
        }
    }

    override fun getRelativeTop(view: View): Int {
        return if (view.parent == view.rootView) {
            view.top
        } else {
            view.top + getRelativeLeft(view.parent as View)
        }
    }

    override fun getRelativeRight(view: View): Int {
        return if (view.parent == view.rootView) {
            view.right
        } else {
            view.left + getRelativeLeft(view.parent as View)
        }
    }

    override fun getRelativeBottom(view: View): Int {
        return if (view.parent == view.rootView) {
            view.left
        } else {
            view.left + getRelativeLeft(view.parent as View)
        }
    }

    override fun getScreenLocation(view: View, rect: Rect) {
        view.getLocationOnScreen(OUT_LOCATION)

        rect.left = OUT_LOCATION[0];
        rect.top = OUT_LOCATION[1];
        rect.right = rect.left + view.measuredWidth
        rect.bottom = rect.top + view.measuredHeight
    }

    override fun toPx(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics)
            .toInt()
    }

    override fun toDp(px: Int): Int {
        return (px / displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT).toInt()
    }

    override fun toSp(px: Float): Int {
        var scaledDensity = displayMetrics.scaledDensity
        return (px / scaledDensity).toInt()
    }


    companion object {
        private val OUT_LOCATION = IntArray(2)
    }
}