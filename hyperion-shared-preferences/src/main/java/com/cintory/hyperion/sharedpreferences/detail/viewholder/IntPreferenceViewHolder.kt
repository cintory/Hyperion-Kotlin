package com.cintory.hyperion.sharedpreferences.detail.viewholder

import android.content.SharedPreferences
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.cintory.hyperion.sharedpreferences.R
import java.lang.Exception

/**
 * Created by Cintory on 2018/9/10 13:49
 * Email：Cintory@gmail.com
 */
class IntPreferenceViewHolder(itemView: View, sharedPreferences: SharedPreferences) :
    PreferenceViewHolder<Int>(itemView) {

    private val editTextValue: EditText

    init {
        editTextValue = itemView.findViewById(R.id.hsp_navigation_preference_value)
        editTextValue.setOnEditorActionListener(EditorListener(sharedPreferences))
    }

    override fun bind(preferenceKey: String, preferenceValue: Int) {
        super.bind(preferenceKey, preferenceValue)
        editTextValue.setText(preferenceValue.toString())
    }

    private inner class EditorListener(sharedPreferences: SharedPreferences) :
        SharedPreferenceEditorListener(sharedPreferences) {
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            try {
                var value = v?.text.toString().toInt()
                if (value != sharedPreferences.getInt(getKey(), 0)) {
                    sharedPreferences.edit()
                        .putInt(getKey(), value)
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
