package com.cintory.hyperion.sample

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.cintory.hyperion.core.Hyperion

/**
 * Created by Cintory on 2018/9/5 15:11
 * Email：Cintory@gmail.com
 */
class GestureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gesture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val openMenuButton = view.findViewById<Button>(R.id.open_button)
        openMenuButton.setOnClickListener { Hyperion.open(activity as Activity) }
    }
}