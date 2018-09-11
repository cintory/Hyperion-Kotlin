package com.cintory.hyperion.core.internal

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * Created by Cintory on 2018/9/5 14:46
 * Email：Cintory@gmail.com
 */
class UiThreadExecutor : Executor {

    override fun execute(command: Runnable?) {
        handler.post(command)
    }

    companion object {
        private val handler = Handler(Looper.getMainLooper())
    }
}