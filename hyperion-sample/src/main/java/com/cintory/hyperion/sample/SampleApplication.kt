package com.cintory.hyperion.sample

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import java.util.*

/**
 * Created by Cintory on 2018/9/11 11:35
 * Email：Cintory@gmail.com
 */
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val stringSet = LinkedHashSet(Arrays.asList("Hello", "Hyperion!"))
        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putBoolean("KEY_BOOLEAN", true)
            .putFloat("KEY_FLOAT", 12.34f)
            .putInt("KEY_INT", 1234)
            .putLong("KEY_LONG", 1234L)
            .putString("KEY_STRING", "Hello Hyperion!")
            .putStringSet("KEY_STRING_SET", stringSet)
            .apply()

        getSharedPreferences("sample_prefs", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("KEY_BOOLEAN", true)
            .apply()

    }
}