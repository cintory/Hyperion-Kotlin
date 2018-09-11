package com.cintory.hyperion.crash

import android.content.Context
import com.cintory.hyperion.plugin.v1.Plugin

import com.google.auto.service.AutoService
import kotlin.concurrent.thread


/**
 * Created by Cintory on 2018/9/6 10:46
 * Email：Cintory@gmail.com
 */
@AutoService(Plugin::class)
class CrashPlugin : Plugin() {

    override fun onApplicationCreated(context: Context) {
        var handler = Thread.getDefaultUncaughtExceptionHandler();
        handler = if (handler == null) {
            Handler(context)
        } else {
            Wrapper(handler, Handler(context))
        }
        Thread.setDefaultUncaughtExceptionHandler(handler)
    }

    private class Handler(private val context: Context) : Thread.UncaughtExceptionHandler {

        val reportFactory: ReportFactory

        init {
            reportFactory = ReportFactory(context)
        }

        override fun uncaughtException(t: Thread?, e: Throwable?) {
            e ?: return
            CrashActivity.start(context, reportFactory.createReport(e))
        }

    }

    private class Wrapper(
        private val base: Thread.UncaughtExceptionHandler, private val
        handler: Handler
    ) : Thread.UncaughtExceptionHandler {

        override fun uncaughtException(t: Thread?, e: Throwable?) {
            handler.uncaughtException(t, e)
            base.uncaughtException(t, e)
        }

    }
}