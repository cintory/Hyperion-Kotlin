package com.cintory.hyperion.crash

import android.content.Context
import android.os.Build

/**
 * Created by Cintory on 2018/9/6 11:19
 * Email：Cintory@gmail.com
 */
class ReportFactory(private val context: Context) {


    fun createReport(error: Throwable): Report {
        val report = Report()
        report.header = getHeaderText()
        report.trace = createStackTraceString(error)
        return report
    }

    private fun getHeaderText(): String {

        val sb = StringBuilder()

        sb.append(createKeyValueLine("Application", context.packageName))
        sb.append(createKeyValueLine("Manufacturer", Build.MANUFACTURER))
        sb.append(createKeyValueLine("Model", Build.MODEL))
        sb.append(createKeyValueLine("Brand", Build.BRAND))
        sb.append(createKeyValueLine("Device", Build.DEVICE))
        sb.append(createKeyValueLine("Board", Build.BOARD))
        sb.append(createKeyValueLine("Hardware", Build.HARDWARE))
        sb.append(createKeyValueLine("Product", Build.PRODUCT))
        sb.append(createKeyValueLine("Android Version", Build.VERSION.RELEASE))

        return sb.toString()
    }

    private fun createStackTraceString(error: Throwable): String {
        val sb = StringBuilder()
        sb.append(error.javaClass.canonicalName)
        sb.append(": ")
        sb.append(error.message)
        sb.append("\n")

        var stackCount = 0
        val stackTrace = error.stackTrace
        for (e in stackTrace) {
            sb.append("   ")
            sb.append(e.toString())
            sb.append("\n")
            if (stackCount >= MAX_STACK_HEIGHT) {
                sb.append("\n")
                sb.append(stackTrace.size - stackCount)
                sb.append(" more")
                break
            }
            stackCount++
        }
        val cause = error.cause
        if (cause != null && error != cause && stackCount < MAX_STACK_HEIGHT) {
            sb.append("Caused by:")
            sb.append("\n")
            sb.append(createStackTraceString(cause))
        }
        return sb.toString()
    }

    private fun createKeyValueLine(key: String, value: String): String {
        return "$key: $value\n"
    }

    companion object {
        private val MAX_STACK_HEIGHT = 80
    }
}