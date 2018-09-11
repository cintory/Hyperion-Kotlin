package com.cintory.hyperion.plugin.v1

import android.graphics.Rect
import android.support.annotation.Px
import android.view.View

/**
 * Created by Cintory on 2018/8/29 15:34
 * Email：Cintory@gmail.com
 */
interface MeasurementHelper {

    fun getParentRelativeRect(view: View, rect: Rect)

    fun getRelativeLeft(view: View): Int

    fun getRelativeTop(view: View): Int

    fun getRelativeRight(view: View): Int

    fun getRelativeBottom(view: View): Int

    fun getScreenLocation(view: View, rect: Rect)

    @Px
    fun toPx(dp: Int): Int

    fun toDp(@Px px: Int): Int

    fun toSp(px: Float): Int
}