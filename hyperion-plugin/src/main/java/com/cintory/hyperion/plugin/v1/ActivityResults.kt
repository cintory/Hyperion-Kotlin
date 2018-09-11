package com.cintory.hyperion.plugin.v1

import android.content.Intent
import android.os.Bundle

/**
 * Created by Cintory on 2018/8/29 15:25
 * Email：Cintory@gmail.com
 */
interface ActivityResults {

    fun register(listener: Listener)

    fun unregister(listener: Listener): Boolean

    fun startActivityForResult(intent: Intent, requestCode: Int)

    fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?)

    interface Listener {
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}