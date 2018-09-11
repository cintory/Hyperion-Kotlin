package com.cintory.hyperion.sharedpreferences.detail.viewholder

import android.content.SharedPreferences
import android.widget.TextView

/**
 * Created by Cintory on 2018/9/10 13:56
 * Email：Cintory@gmail.com
 */
abstract class SharedPreferenceEditorListener(protected val sharedPreferences: SharedPreferences) :
    TextView.OnEditorActionListener