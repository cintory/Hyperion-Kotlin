package com.cintory.hyperion.sample

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

/**
 * Created by Cintory on 2018/9/5 14:53
 * Email：Cintory@gmail.com
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (savedInstanceState == null) {
            val handler = Handler()
            handler.postDelayed({
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            }, 2000)
        }
    }
}