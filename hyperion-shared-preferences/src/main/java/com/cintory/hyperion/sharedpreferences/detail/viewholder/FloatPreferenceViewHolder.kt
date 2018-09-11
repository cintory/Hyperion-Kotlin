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
 * Created by Cintory on 2018/9/10 14:27
 * Email：Cintory@gmail.com
 */
class FloatPreferenceViewHolder(itemView: View, sharedPreferences: SharedPreferences) :
    PreferenceViewHolder<Float>(itemView) {

    private val editTextValue: EditText

    init {
        editTextValue = itemView.findViewById(R.id.hsp_navigation_preference_value)
        editTextValue.setOnEditorActionListener(EditorListener(sharedPreferences))
    }

    override fun bind(preferenceKey: String, preferenceValue: Float) {
        super.bind(preferenceKey, preferenceValue)
        editTextValue.setText(preferenceValue.toString())
    }

    private inner class EditorListener(sharedPreferences: SharedPreferences) :
        SharedPreferenceEditorListener(sharedPreferences) {

        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            try {
                var value = v?.text.toString().toFloat()
                if (!floatEquals(value, sharedPreferences.getFloat(getKey(), 0f))) {
                    sharedPreferences.edit()
                        .putFloat(getKey(), value)
                        .apply()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Unable to parse integer", e)
            }
            return true
        }
    }

    companion object {

        private val TAG = "FloatPrefViewHolder"

        private fun floatEquals(left: Float, right: Float): Boolean {
            return Math.abs(left - right) < 1e-12
        }
    }
}