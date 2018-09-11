package com.cintory.hyperion.core.internal

import android.app.Fragment
import android.content.Intent
import com.cintory.hyperion.plugin.v1.ActivityResults

/**
 * Created by Cintory on 2018/9/4 10:50
 * Email：Cintory@gmail.com
 */
class ActivityResultsFragment : Fragment(), ActivityResults {

    private val listeners = ArrayList<ActivityResults.Listener>()

    override fun register(listener: ActivityResults.Listener) {
        listeners.add(listener)
    }

    override fun unregister(listener: ActivityResults.Listener): Boolean {
        return listeners.remove(listener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        listeners.forEach { it.onActivityResult(requestCode, resultCode, data) }
    }
}