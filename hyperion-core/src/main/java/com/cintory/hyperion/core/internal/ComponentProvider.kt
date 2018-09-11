package com.cintory.hyperion.core.internal

import android.content.Context

/**
 * Created by Cintory on 2018/8/31 11:17
 * Email：Cintory@gmail.com
 */
class ComponentProvider {

    init {
        throw AssertionError("No instance.")
    }

    companion object {

        val COMPONENT = "component"

        fun get(context: Context): CoreComponent {
            return context.getSystemService(COMPONENT) as CoreComponent
        }
    }

}