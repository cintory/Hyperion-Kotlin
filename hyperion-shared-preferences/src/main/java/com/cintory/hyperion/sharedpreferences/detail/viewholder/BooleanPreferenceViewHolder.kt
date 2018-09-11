package com.cintory.hyperion.sharedpreferences.detail.viewholder

import android.content.SharedPreferences
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import com.cintory.hyperion.sharedpreferences.R

/**
 * Created by Cintory on 2018/9/10 14:16
 * Email：Cintory@gmail.com
 */
class BooleanPreferenceViewHolder(itemView: View, sharedPreferences: SharedPreferences) :
    PreferenceViewHolder<Boolean>(itemView) {

    private val switchValue: Switch

    init {
        switchValue = itemView.findViewById(R.id.hsp_navigation_preference_value)
        switchValue.setOnCheckedChangeListener(CheckChangedListener(sharedPreferences))
    }

    override fun bind(preferenceKey: String, preferenceValue: Boolean) {
        super.bind(preferenceKey, preferenceValue)
        switchValue.isChecked = preferenceValue
    }

    private inner class CheckChangedListener(private val sharedPreferences: SharedPreferences) :
        CompoundButton.OnCheckedChangeListener {

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            if (sharedPreferences.getBoolean(getKey(), false) != isChecked) {
                sharedPreferences.edit()
                    .putBoolean(getKey(), isChecked)
                    .apply()
            }
        }
    }
}