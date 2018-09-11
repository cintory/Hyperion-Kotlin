package com.cintory.hyperion.sharedpreferences.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.cintory.hyperion.sharedpreferences.R
import java.io.File
import java.util.*

/**
 * Created by Cintory on 2018/9/7 13:54
 * Email：Cintory@gmail.com
 */
class SharedPreferencesListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hsp_activity_shared_preferences_list)
        setSupportActionBar(findViewById(R.id.hsp_toolbar))
        var actionBr = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        val preference = getPreferences()
        val recyclerView = findViewById<RecyclerView>(R.id.hsp_prefs_recycler)
        recyclerView.adapter = SharedPreferencesListAdapter(preference)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun getPreferences(): MutableList<String> {
        var prefsDirectory = File(applicationInfo.dataDir, PREFS_DIRECTORY)

        if (prefsDirectory.exists() && prefsDirectory.isDirectory) {
            var list: Array<String> = prefsDirectory.list() ?: return Collections.emptyList()
            removeExtensions(list)
            return Arrays.asList(*list)
        } else {
            return Collections.emptyList()
        }
    }

    private fun removeExtensions(list: Array<String>) {
        for (i in list.indices) {
            val item = list[i]
            list[i] = removeExtension(item)
        }
    }

    private fun removeExtension(str: String): String {
        var index = str.lastIndexOf('.')
        if (index > 0) {
            return str.substring(0, index)
        }
        return str
    }

    companion object {
        private val PREFS_DIRECTORY = "shared_prefs"
    }
}