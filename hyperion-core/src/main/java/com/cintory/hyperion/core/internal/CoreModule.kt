package com.cintory.hyperion.core.internal

import android.content.ServiceConnection
import com.cintory.hyperion.plugin.v1.AttributeTranslator
import com.cintory.hyperion.plugin.v1.MeasurementHelper
import dagger.Binds
import dagger.Module

/**
 * Created by Cintory on 2018/8/30 11:31
 * Email：Cintory@gmail.com
 */

@Module
 abstract class CoreModule {

    @Binds
    @ActivityScope
     abstract fun bindServiceConnection(connection: HyperionService.Connection): ServiceConnection

    @Binds
    @ActivityScope
     abstract fun bindMeasurementHelper(measurementHelper: MeasurementHelperImpl): MeasurementHelper

    @Binds
    @ActivityScope
     abstract fun bindAttributeTranslator(attributeTranslator: AttributeTranslatorImpl): AttributeTranslator

}