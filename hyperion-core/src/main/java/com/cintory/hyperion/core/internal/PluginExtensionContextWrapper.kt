package com.cintory.hyperion.core.internal

import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import com.cintory.hyperion.plugin.v1.ExtensionProvider
import com.cintory.hyperion.plugin.v1.PluginExtension

/**
 * Created by Cintory on 2018/8/31 15:05
 * Email：Cintory@gmail.com
 */
class PluginExtensionContextWrapper(base: Context, private val extension: PluginExtension) :
    ContextWrapper(base) {

    private var inflater: LayoutInflater? = null

    override fun getSystemService(name: String?): Any {
        if (Context.LAYOUT_INFLATER_SERVICE == name) {
            if (inflater == null) {
                inflater = LayoutInflater.from(baseContext).cloneInContext(this)
            }
            return inflater!!
        }
        if (ExtensionProvider.NAME == name) {
            return extension
        }

        return super.getSystemService(name)
    }

}