package com.cintory.hyperion.sharedpreferences.detail.viewholder

import android.content.SharedPreferences
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.cintory.hyperion.sharedpreferences.R

/**
 * Created by Cintory on 2018/9/10 16:11
 * Email：Cintory@gmail.com
 */
class StringPreferenceViewHolder(itemView: View, sharedPreferences: SharedPreferences) :
    PreferenceViewHolder<String>(itemView) {

    private val editTextValue: EditText

    init {
        editTextValue = itemView.findViewById(R.id.hsp_navigation_preference_value)
        editTextValue.setOnEditorActionListener(EditorListener(sharedPreferences))
    }

    override fun bind(preferenceKey: String, preferenceValue: String) {
        super.bind(preferenceKey, preferenceValue)
    }

    private inner class EditorListener(sharedPreferences: SharedPreferences) :
        SharedPreferenceEditorListener(sharedPreferences) {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            var value = v?.text.toString()
            if (!value.equals(sharedPreferences.getString(getKey(), ""))) {
                sharedPreferences.edit()
                    .putString(getKey(), value)
                    .apply()
            }
            return true
        }
    }
}