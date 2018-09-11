package com.cintory.hyperion.sharedpreferences.detail.viewholder

import android.content.SharedPreferences
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.cintory.hyperion.sharedpreferences.R

/**
 * Created by Cintory on 2018/9/10 15:57
 * Email：Cintory@gmail.com
 */
class LongPreferenceViewHolder(itemView: View, sharedPreferences: SharedPreferences) :
    PreferenceViewHolder<Long>(itemView) {

    private val editTextValue: EditText

    init {
        editTextValue = itemView.findViewById(R.id.hsp_navigation_preference_value)
        editTextValue.setOnEditorActionListener(EditorListener(sharedPreferences))
    }

    override fun bind(preferenceKey: String, preferenceValue: Long) {
        super.bind(preferenceKey, preferenceValue)
        editTextValue.setText(preferenceValue.toString())
    }

    private inner class EditorListener(sharedPreferences: SharedPreferences) :
        SharedPreferenceEditorListener(sharedPreferences) {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            try {
                val value = v?.text.toString().toLong()
                if (value != sharedPreferences.getLong(getKey(), 0)) {
                    sharedPreferences.edit()
                        .putLong(getKey(), value)
                        .apply()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Unable to parse integer", e)
            }
            return true
        }
    }

    companion object {
        private val TAG = "IntPrefViewHolder"
    }
}