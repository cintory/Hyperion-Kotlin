package com.cintory.hyperion.sharedpreferences.detail

import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.cintory.hyperion.sharedpreferences.R
import com.cintory.hyperion.sharedpreferences.detail.viewholder.*
import java.util.*

/**
 * Created by Cintory on 2018/9/7 15:55
 * Email：Cintory@gmail.com
 */
class SharedPreferencesDetailAdapter(private val sharedPreferences: SharedPreferences) :
    RecyclerView.Adapter<PreferenceViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferenceViewHolder<*> {
        var inflate = LayoutInflater.from(parent.context)

        when (viewType) {
            VIEW_TYPE_INTEGER -> return IntPreferenceViewHolder(
                inflate.inflate(
                    R.layout.hsp_navigation_preference_number_viewholder,
                    parent,
                    false
                ), sharedPreferences
            )
            VIEW_TYPE_LONG -> return LongPreferenceViewHolder(
                inflate.inflate(
                    R.layout.hsp_navigation_preference_number_viewholder,
                    parent,
                    false
                ), sharedPreferences
            )
            VIEW_TYPE_FLOAT -> return FloatPreferenceViewHolder(
                inflate.inflate(
                    R.layout.hsp_navigation_preference_number_viewholder,
                    parent,
                    false
                ), sharedPreferences
            )
            VIEW_TYPE_BOOLEAN -> return BooleanPreferenceViewHolder(
                inflate.inflate(
                    R.layout.hsp_navigation_preference_boolean_viewholder,
                    parent,
                    false
                ), sharedPreferences
            )
            VIEW_TYPE_STRING -> return StringPreferenceViewHolder(
                inflate.inflate(
                    R.layout.hsp_navigation_preference_string_viewholder,
                    parent,
                    false
                ), sharedPreferences
            )
            VIEW_TYPE_STRING_SET -> return StringSetPreferenceViewHolder(
                inflate.inflate(
                    R.layout.hsp_navigation_preference_string_viewholder,
                    parent,
                    false
                ), sharedPreferences
            )
            else -> throw RuntimeException("Unknown view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        var preferenceValue: Any = getValue(position)
        return when (preferenceValue) {
            is Int -> VIEW_TYPE_INTEGER
            is Long -> VIEW_TYPE_LONG
            is Float -> VIEW_TYPE_FLOAT
            is Boolean -> VIEW_TYPE_BOOLEAN
            is String -> VIEW_TYPE_STRING
            is Set<*> -> VIEW_TYPE_STRING_SET
            else -> throw RuntimeException("Unknown preference type: " + preferenceValue.javaClass.simpleName)
        }
    }

    override fun getItemCount(): Int {
        return getKeys().size
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder<*>, position: Int) {
        var key = getKey(position)
        var preferenceValue = getSharedPreferencesMap()[key]
        if (preferenceValue != null)
            (holder as PreferenceViewHolder<Any>).bind(key, preferenceValue)
    }

    private fun <T> getValue(position: Int): T {
        var key = getKey(position)
        return getSharedPreferencesMap()[key] as T
    }

    fun notifyKeyChanged(key: String) {
        var index = getKeysSorted().indexOf(key)
        notifyItemChanged(index)
    }

    private fun getKey(position: Int): String {
        return getKeysSorted()[position]
    }

    private fun getSharedPreferencesMap(): Map<String, *> {
        return sharedPreferences.all
    }

    private fun getKeys(): Set<String> {
        return getSharedPreferencesMap().keys
    }

    private fun getKeysSorted(): List<String> {
        val sortedList = ArrayList(getKeys())
        sortedList.sort()
        return sortedList
    }

    companion object {

        private val VIEW_TYPE_INTEGER = 1
        private val VIEW_TYPE_LONG = 2
        private val VIEW_TYPE_FLOAT = 3
        private val VIEW_TYPE_BOOLEAN = 4
        private val VIEW_TYPE_STRING = 5
        private val VIEW_TYPE_STRING_SET = 6

    }

}

