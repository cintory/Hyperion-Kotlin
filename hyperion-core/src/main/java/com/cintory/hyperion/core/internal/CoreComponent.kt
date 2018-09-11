package com.cintory.hyperion.core.internal

import android.app.Activity
import android.content.ServiceConnection
import com.cintory.hyperion.core.PluginSource
import com.cintory.hyperion.plugin.v1.ActivityResults
import com.cintory.hyperion.plugin.v1.AttributeTranslator
import com.cintory.hyperion.plugin.v1.MeasurementHelper
import com.cintory.hyperion.plugin.v1.OverlayContainer
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [CoreModule::class, ActivityModule::class],
    dependencies = [AppComponent::class]
)
interface CoreComponent {

    fun inject(view: HyperionPluginView)

    fun getMeasurementHelper(): MeasurementHelper

    fun getAttributeTranslator(): AttributeTranslator

    fun getActivity(): Activity

    fun getOverlayContainer(): OverlayContainer

    fun getActivityResults(): ActivityResults

    fun getServiceConnection(): ServiceConnection

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent): Builder

        @BindsInstance
        fun activity(activity: Activity): Builder

        @BindsInstance
        fun pluginSource(pluginSource: PluginSource): Builder

        @BindsInstance
        fun overlayContainer(overlayContainer: OverlayContainer): Builder

        @BindsInstance
        fun activityResults(activityResults: ActivityResults): Builder

        fun build(): CoreComponent
    }

}