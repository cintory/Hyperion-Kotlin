package com.cintory.hyperion.plugin.v1

import android.support.annotation.NonNull

/**
 * Created by Cintory on 2018/8/29 15:39
 * Email：Cintory@gmail.com
 */
interface HyperionMenu {

    fun getMenuState(): MenuState

    fun setMenuState(menuState: MenuState)

    fun addOnMenuStateChangedListener(listener: OnMenuStateChangedListener)
}