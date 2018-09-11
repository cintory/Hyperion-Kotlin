package com.cintory.hyperion.sharedpreferences.detail.viewholder

import android.content.SharedPreferences
import android.view.View
import android.widget.EditText
import com.cintory.hyperion.sharedpreferences.R
import java.util.*

/**
 * Created by Cintory on 2018/9/10 16:24
 * Email：Cintory@gmail.com
 */
class StringSetPreferenceViewHolder(itemView: View, sharedPreferences: SharedPreferences) :
    PreferenceViewHolder<Set<String>>(itemView) {

    private val editTextValue: EditText

    init {
        editTextValue = itemView.findViewById(R.id.hsp_navigation_preference_value)
        editTextValue.isEnabled = false
    }

    override fun bind(preferenceKey: String, preferenceValue: Set<String>) {
        super.bind(preferenceKey, preferenceValue)
        val displayValue = Arrays.toString(preferenceValue.toTypedArray())
        editTextValue.setText(displayValue)
    }
}