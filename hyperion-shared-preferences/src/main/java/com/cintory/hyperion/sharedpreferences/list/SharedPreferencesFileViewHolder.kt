package com.cintory.hyperion.sharedpreferences.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.cintory.hyperion.sharedpreferences.R

/**
 * Created by Cintory on 2018/9/7 14:03
 * Email：Cintory@gmail.com
 */
class SharedPreferencesFileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    private val label: TextView
    private var prefsName: String = ""

    init {
        label = itemView.findViewById(R.id.hsp_pref_name)
        itemView.setOnClickListener(this)
    }

    fun bind(prefsName: String) {
        this.prefsName = prefsName

    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}