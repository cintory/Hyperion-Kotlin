package com.cintory.hyperion.core.internal

import android.app.Application
import android.content.Context
import com.cintory.hyperion.core.PublicControl
import com.cintory.hyperion.plugin.v1.ApplicationExtension
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [(AppModule::class)])
interface AppComponent {

    val application: Application
    val applicationExtension: ApplicationExtension
    val activityLifecycleCallbacks: Application.ActivityLifecycleCallbacks
    val publicControl: PublicControl
    val pluginRepository: PluginRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(application: Application): Builder

        fun build(): AppComponent
    }

    class Holder private constructor() {

        init {
            throw AssertionError("No instances.")
        }

        companion object {
            @Volatile
            private var instance: AppComponent? = null

            fun getInstance(context: Context): AppComponent {
                if (instance == null) {
                    synchronized(Holder::class.java) {
                        if (instance == null) {
                            instance = DaggerAppComponent.builder()
                                .app(context.applicationContext as Application)
                                .build()
                        }
                    }
                }
                return instance!!
            }
        }
    }
}