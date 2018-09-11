package com.cintory.hyperion.plugin.v1

import android.annotation.SuppressLint
import android.content.Context

/**
 * Created by Cintory on 2018/8/29 16:14
 * Email：Cintory@gmail.com
 */
class ExtensionProvider {


    private fun ExtensionProvider() {
        throw  AssertionError("No instances.")
    }

    companion object {
        val NAME = "extension"

        @SuppressLint("WrongConstant")
        fun get(context: Context): PluginExtension {
            return context.getSystemService(NAME) as PluginExtension
        }
    }
}