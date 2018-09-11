package com.cintory.hyperion.plugin.v1

import android.support.annotation.Px

/**
 * Created by Cintory on 2018/8/29 15:32
 * Email：Cintory@gmail.com
 */
interface AttributeTranslator {

    fun translateDp(dp: Int): String

    fun translatePx(@Px px: Int): String

    fun translatePxToSp(px: Int): String
}