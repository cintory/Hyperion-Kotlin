package com.cintory.hyperion.core.internal

import com.cintory.hyperion.plugin.v1.AttributeTranslator
import com.cintory.hyperion.plugin.v1.MeasurementHelper
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/4 15:22
 * Email：Cintory@gmail.com
 */
class AttributeTranslatorImpl @Inject constructor(private val measurementHelper: MeasurementHelper) :
    AttributeTranslator {
    override fun translateDp(dp: Int): String {
        return dp.toString() + " dp, " + measurementHelper.toPx(dp) + " px"
    }

    override fun translatePx(px: Int): String {
        return measurementHelper.toDp(px).toString() + " dp, " + px + " px"
    }

    override fun translatePxToSp(px: Int): String {
        return measurementHelper.toSp(px.toFloat()).toString() + " sp, " + px + " px"
    }
}