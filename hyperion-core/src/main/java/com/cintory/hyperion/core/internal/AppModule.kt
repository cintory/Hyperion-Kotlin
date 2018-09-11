package com.cintory.hyperion.core.internal

import android.app.Application
import com.cintory.hyperion.core.PluginSource
import com.cintory.hyperion.core.PublicControl
import com.cintory.hyperion.plugin.v1.ApplicationExtension
import com.cintory.hyperion.plugin.v1.ForegroundManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
abstract class AppModule {

    @AppScope
    @Binds
    abstract fun bindFilter(filter: HyperionIgnoreFilter): Application.ActivityLifecycleCallbacks

    @AppScope
    @Binds
    abstract fun bindApplicationExtension(extension: ApplicationExtensionImpl): ApplicationExtension

    @AppScope
    @Binds
    abstract fun bindPublicControl(control: PublicControlImpl): PublicControl

    @AppScope
    @Binds
    abstract fun bindForegroundManager(foregroundManager: ForegroundManagerImpl): ForegroundManager

    @AppScope
    @Binds
    @IntoSet
    abstract fun bindServiceDelegate(delegate: HyperionServiceLifecycleDelegate): LifecycleDelegate

    @AppScope
    @Binds
    @IntoSet
    abstract fun bindComponentDelegate(delegate: InstallationLifecycleDelegate): LifecycleDelegate

    @AppScope
    @Binds
    @IntoSet
    abstract fun bindForegroundDelegate(delegate: ForegroundManagerLifecycleDelegate): LifecycleDelegate

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providePluginSource(container: CoreComponentContainer): PluginSource {
            return container.pluginSource
        }
    }

}