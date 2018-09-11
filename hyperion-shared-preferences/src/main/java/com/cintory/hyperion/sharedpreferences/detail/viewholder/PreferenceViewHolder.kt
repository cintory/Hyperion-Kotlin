package com.cintory.hyperion.sharedpreferences.detail.viewholder

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.cintory.hyperion.sharedpreferences.R

/**
 * Created by Cintory on 2018/9/7 16:09
 * Email：Cintory@gmail.com
 */
abstract class PreferenceViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val textViewKey: TextView

    init {
        textViewKey = itemView.findViewById(R.id.hsp_navigation_preference_key)
    }

    @CallSuper
    open fun bind(preferenceKey: String, preferenceValue: T) {
        textViewKey.text = preferenceKey
    }

    protected fun getKey(): String {
        return textViewKey.text.toString()
    }
}