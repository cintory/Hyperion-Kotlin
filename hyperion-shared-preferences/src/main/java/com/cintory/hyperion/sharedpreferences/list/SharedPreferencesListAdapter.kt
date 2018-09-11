package com.cintory.hyperion.sharedpreferences.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cintory.hyperion.sharedpreferences.R

/**
 * Created by Cintory on 2018/9/7 14:02
 * Email：Cintory@gmail.com
 */
class SharedPreferencesListAdapter( preferences: MutableList<String>) :
    RecyclerView.Adapter<SharedPreferencesFileViewHolder>() {

    private val preferences: MutableList<String>

    init {
        this.preferences = ArrayList(preferences)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SharedPreferencesFileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.hsp_navigation_preference_file_viewholder, parent, false
        )
        return SharedPreferencesFileViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return preferences.size
    }

    override fun onBindViewHolder(holder: SharedPreferencesFileViewHolder, position: Int) {
        holder.bind(preferences[position])
    }

}