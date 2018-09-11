package com.cintory.hyperion.sharedpreferences.detail

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.widget.FrameLayout
import com.cintory.hyperion.sharedpreferences.R

/**
 * Created by Cintory on 2018/9/7 15:56
 * Email：Cintory@gmail.com
 */
class SharedPreferencesNavigationView(context: Context) : FrameLayout(context) {

    private val preferenceListener = PreferenceListener()

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesDetailAdapter: SharedPreferencesDetailAdapter

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val activity = context as Activity
        val prefsName =
            activity.intent.getStringExtra(SharedPreferencesDetailActivity.KEY_PREFS_NAME)
        sharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE) ?: throw
        RuntimeException("No sharedPreferences found")
        sharedPreferencesDetailAdapter = SharedPreferencesDetailAdapter(sharedPreferences)
        var recyclerView = findViewById<RecyclerView>(R.id.hsp_navigation_recycler)
        recyclerView.adapter = sharedPreferencesDetailAdapter

        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceListener)
    }

    private inner class PreferenceListener : SharedPreferences.OnSharedPreferenceChangeListener {
        override fun onSharedPreferenceChanged(
            sharedPreferences: SharedPreferences?,
            key: String?
        ) {
            key ?: return
            sharedPreferencesDetailAdapter.notifyKeyChanged(key)
        }
    }
}

