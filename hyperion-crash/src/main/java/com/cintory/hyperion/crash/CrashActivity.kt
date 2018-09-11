package com.cintory.hyperion.crash

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cintory.hyperion.plugin.v1.HyperionIgnore

/**
 * Created by Cintory on 2018/9/6 10:25
 * Email：Cintory@gmail.com
 */
@HyperionIgnore
class CrashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var report: Report

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hc_activity)
        report = intent.getSerializableExtra(EXTRA_REPORT) as Report

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val headerText = findViewById<TextView>(R.id.header)
        val stacktraceText = findViewById<TextView>(R.id.stacktrace)
        val container = findViewById<View>(R.id.container)

        fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val text = getExternalText()
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share"))
        }

        stacktraceText.setHorizontallyScrolling(true)
        stacktraceText.movementMethod = ScrollingMovementMethod()
        headerText.text = report.header
        stacktraceText.text = report.trace
        container.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboard != null) {
            val clip = ClipData.newPlainText("Hyperion Crash Report", getExternalText())
            clipboard.primaryClip = clip
            Toast.makeText(this, "Copied crash report to clipboard", Toast.LENGTH_LONG).show()
        }
    }


    private fun getExternalText(): String {
        return report.header + "\n\n" + report.trace
    }


    companion object {
        private val EXTRA_REPORT = "hr_report"

        public fun start(context: Context, report: Report) {
            val intent = Intent(context, CrashActivity::class.java)
            intent.putExtra(EXTRA_REPORT, report)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}