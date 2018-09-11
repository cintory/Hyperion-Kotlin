package com.cintory.hyperion.sharedpreferences.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cintory.hyperion.plugin.v1.HyperionIgnore
import com.cintory.hyperion.sharedpreferences.R

/**
 * Created by Cintory on 2018/9/7 15:55
 * Email：Cintory@gmail.com
 */
@HyperionIgnore
class SharedPreferencesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hsp_activity_shared_preferences_detail)
        setSupportActionBar(findViewById(R.id.hsp_toolbar))
        var actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = intent.getStringExtra(KEY_PREFS_NAME)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        public val KEY_PREFS_NAME = "prefs_name"

        public fun start(context: Context, prefsName: String) {
            var intent = Intent(context, SharedPreferencesDetailActivity::class.java)
            intent.putExtra(KEY_PREFS_NAME, prefsName)
            context.startActivity(intent)
        }

    }
}