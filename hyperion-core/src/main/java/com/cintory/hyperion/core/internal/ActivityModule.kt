package com.cintory.hyperion.core.internal

import android.app.Activity
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideLayoutInflater(activity: Activity): LayoutInflater {
        return LayoutInflater.from(activity)
    }

    @Provides
    fun provideResources(activity: Activity): Resources {
        return activity.resources
    }

    @Provides
    fun provideDisplayMetrics(resources: Resources): DisplayMetrics {
        return resources.displayMetrics
    }

}