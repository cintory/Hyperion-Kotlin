package com.cintory.hyperion.plugin.v1

import android.content.Context
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cintory.hyperion.plugin.R

/**
 * Created by Cintory on 2018/8/29 15:01
 * Email：Cintory@gmail.com
 */
abstract class PluginModule {

    private var extension: PluginExtension? = null
    private var context: Context? = null

    fun create(extension: PluginExtension, context: Context) {
        this.extension = extension
        this.context = context
        onCreate()
    }

    /**
     *
     */
    @StringRes
    fun getName(): Int {
        return R.string.hype_module_name
    }

    protected fun onCreate() {

    }

    abstract fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View

    fun destory() {
        this.extension = null
        this.context = null
        onDestroy()
    }

    protected fun onDestroy() {

    }

    fun getExtension(): PluginExtension? {
        return this.extension
    }

    fun getContext(): Context? {
        return this.context
    }


}


