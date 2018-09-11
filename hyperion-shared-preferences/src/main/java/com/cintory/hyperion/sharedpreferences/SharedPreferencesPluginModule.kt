package com.cintory.hyperion.sharedpreferences

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cintory.hyperion.plugin.v1.PluginModule
import com.cintory.hyperion.sharedpreferences.list.SharedPreferencesListActivity

/**
 * Created by Cintory on 2018/9/7 11:26
 * Email：Cintory@gmail.com
 */
class SharedPreferencesPluginModule : PluginModule(), View.OnClickListener {

    override fun onClick(v: View?) {
        var intent = Intent(getContext(), SharedPreferencesListActivity::class.java)
        getContext()?.startActivity(intent)

    }

    override fun createPluginView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        var view = layoutInflater.inflate(R.layout.hsp_item_plugin, parent, false)
        view.setOnClickListener(this)
        return view
    }

}