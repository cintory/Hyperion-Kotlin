package com.cintory.hyperion.core.internal

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View

/**
 * Created by Cintory on 2018/9/3 19:07
 * Email：Cintory@gmail.com
 */
class ActivityUtil {

    init {
        throw AssertionError("No instances.")
    }

    companion object {
        fun findActivity(view: View): Activity? {
            var v = view;
            var activity = findActivity(v.context)
            while (activity == null) {
                if (v.parent !is View)
                    return null
                v = v.parent as View
                activity = findActivity(v.context)
            }
            return activity
        }

        fun findActivity(context: Context): Activity? {
            var c = context
            // assume the context is a FragmentActivity, or a wrapper around a FragmentActivity
            // keep unwrapping the context until you find a FragmentActivity
            while (c !is Activity) {
                if (c !is ContextWrapper) {
                    // no activity at the bottom of the rabbit hole
                    return null
                }
                var contextWrapper: ContextWrapper = c
                if (contextWrapper == contextWrapper.baseContext) {
                    // found infinite rabbit hole
                    return null
                }
                // deeper down the rabbit hole we go
                c = contextWrapper.baseContext
            }
            return c
        }
    }
}