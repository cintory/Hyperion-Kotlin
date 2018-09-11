package com.cintory.hyperion.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Cintory on 2018/8/29 10:41
 * Email：Cintory@gmail.com
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, PagerFragment())
                .commit()
        }
    }
}