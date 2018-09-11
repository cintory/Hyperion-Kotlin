package com.cintory.hyperion.core.internal

import android.app.Application
import android.content.Context
import android.util.Log
import com.cintory.hyperion.core.Hyperion
import java.lang.Exception

/**
 * Created by Cintory on 2018/9/5 14:32
 * Email：Cintory@gmail.com
 */
class HyperionInitProvider : EmptyContentProvider() {
    override fun onCreate(): Boolean {
        return try {
            Hyperion.setApplication(context)
            val application = context.applicationContext as Application
            val component = AppComponent.Holder.getInstance(context)
            application.registerActivityLifecycleCallbacks(component.activityLifecycleCallbacks)
            true
        } catch (e: Exception) {
            Log.e("Hyperion", "Init failed.", e)
            false
        }
    }
}