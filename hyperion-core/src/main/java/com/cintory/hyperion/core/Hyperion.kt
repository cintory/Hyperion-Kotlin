package com.cintory.hyperion.core

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.annotation.RestrictTo
import com.cintory.hyperion.core.internal.AppComponent

/**
 * Created by Cintory on 2018/8/29 10:59
 * Email：Cintory@gmail.com
 */
class Hyperion {

    init {
        throw AssertionError("No instance.")
    }

    companion object {

        private var application: Application? = null

        @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
        fun setApplication(context: Context) {
            application = context.applicationContext as Application
        }


        /**
         * Get the sensitivity threshold of shake detection in G's. Default is 3
         *
         * @return sensitivity Sensitivity of shake detection in G's. Lower is easier to activate.
         *
         */
        fun getShakedGestureSensitivity(): Float {
            requireApplication()
            return AppComponent.Holder.getInstance(application as Context)
                .publicControl
                .getShakeGestureSensitivity()
        }

        /**
         *  Set the sensitivity threshold of shake detection in G's. Default is 3
         *
         *  @param sensitivity Sensitivity of shake detection in G's. Lower is easier to activate
         */
        fun setShakeGestureSensitivity(sensitivity: Float) {
            requireApplication()
            AppComponent.Holder.getInstance(application as Context)
                .publicControl
                .setShakeGestureSensitivity(sensitivity)
        }

        /**
         * Manually trigger the Hyperion menu embedded in the current foreground {@link Activity}
         */
        fun open() {
            requireApplication()
            AppComponent.Holder.getInstance(application as Context)
                .publicControl
                .open()
        }

        /**
         * Manually trigger the Hyperion menu embedded in the given {@link Activity} to open.
         *
         * @param activity the {@link Activity} containing the menu to open
         */
        fun open(activity: Activity) {
            requireApplication()
            AppComponent.Holder.getInstance(application as Context)
                .publicControl
                .open(activity)
        }

        /**
         *  Hook to manually register a plugin source.
         *  This API does not update the Hyperion menu retroactively,
         *  so clients should call this as early as possible
         *
         *  NOTE: For most users, the default {@link java.util.ServiceLoader} implementation will
         *  suffice.
         *
         *  @param pluginSource the {@link PluginSource} invoked in place of the default
         *  implementation.
         */
        fun setPlugins(pluginSource: PluginSource) {
            requireApplication()
            AppComponent.Holder.getInstance(application as Context)
                .publicControl
                .setPluginSource(pluginSource)
        }

        /**
         * Get a plugin source.
         * Client can wrap and delegate the {@link PluginSource}.
         */
        fun getPluginSource(): PluginSource {
            requireApplication()
            return AppComponent.Holder.getInstance(application as Context)
                .publicControl
                .getPluginSource()
        }


        private fun requireApplication() {
            application ?: throw  IllegalStateException("Application has not been set.")
        }
    }
}