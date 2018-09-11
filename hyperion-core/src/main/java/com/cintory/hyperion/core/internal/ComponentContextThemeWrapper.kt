package com.cintory.hyperion.core.internal

import android.content.Context
import android.view.ContextThemeWrapper
import com.cintory.hyperion.core.R

/**
 * Created by Cintory on 2018/9/4 13:31
 * Email：Cintory@gmail.com
 */
class ComponentContextThemeWrapper(base: Context, val component: CoreComponent) :
    ContextThemeWrapper(base, R.style.Hype_Base) {

    override fun getSystemService(name: String?): Any {
        if (name.equals(ComponentProvider.COMPONENT)) {
            return component
        }
        return super.getSystemService(name)
    }
}