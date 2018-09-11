package com.cintory.hyperion.core.internal

import android.annotation.SuppressLint
import android.app.Activity
import android.support.v7.app.AppCompatActivity

/**
 * Created by Cintory on 2018/9/3 19:23
 * Email：Cintory@gmail.com
 */
abstract class FragmentManagerCompat {


    fun isSupport(): Boolean {
        return this is Support
    }

    internal abstract fun <T> findFragmentByTag(tag: String): T?

    internal abstract fun beginTransaction(): FragmentCompatTransaction

    private class Android(private val activity: Activity) : FragmentManagerCompat() {

        override fun <T> findFragmentByTag(tag: String): T? {
            // noinspection unchecked
            return activity.fragmentManager.findFragmentByTag(tag) as T
        }

        @SuppressLint("CommitTransaction")
        override fun beginTransaction(): FragmentCompatTransaction {
            return FragmentCompatTransaction.beginTransaction(activity.fragmentManager.beginTransaction())
        }
    }

    private class Support(private val activity: AppCompatActivity) : FragmentManagerCompat() {

        override fun <T> findFragmentByTag(tag: String): T? {
            //  noinspection unchecked
            return activity.supportFragmentManager.findFragmentByTag(tag) as T
        }

        @SuppressLint("CommitTransaction")
        override fun beginTransaction(): FragmentCompatTransaction {
            return FragmentCompatTransaction.beginTransaction(activity.supportFragmentManager.beginTransaction())
        }

    }

    companion object {
        fun create(activity: Activity): FragmentManagerCompat {
            return if (activity is AppCompatActivity) {
                Support(activity)
            } else {
                Android(activity)
            }
        }
    }
}