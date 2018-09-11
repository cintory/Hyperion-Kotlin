package com.cintory.hyperion.core.internal

import android.app.*
import android.content.*
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.cintory.hyperion.core.R
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * Created by Cintory on 2018/8/30 11:32
 * Email：Cintory@gmail.com
 */
class HyperionService : Service() {

    val binder: IBinder = Binder()
    val actionOpenMenuReceiver = OpenMenuReceiver()
    private var notificationManager: NotificationManager? = null
    private var activity: WeakReference<Activity>? = null

    fun attach(activity: Activity) {
        this.activity = WeakReference(activity)
        initChannels()
        startForeground(NOTIFICATION_ID, createNotification(activity))
    }


    fun detach(activity: Activity) {
        if (this.activity != null) {
            val current = this.activity!!.get()
            if (current == activity) {
                stopForeground(true)
                this.activity = null
            }
        }
    }

    private fun createNotification(activity: Activity): Notification {
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentIntent(createContentPendingIntent())
            .setTicker("")
            .setSmallIcon(R.drawable.hype_logo)
            .setOngoing(true)
            .setVibrate(longArrayOf(0))

        var contentTitle = getString(R.string.hype_notification_content_title)
        var contentText = getString(R.string.hype_notification_content_text)
        var subText = getString(R.string.hype_notification_subtext)

        when {
            Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 -> {
                builder.setContentTitle(contentTitle)
                builder.setContentText(if (contentText.isEmpty()) subText else contentText)
            }
            Build.VERSION.SDK_INT < Build.VERSION_CODES.N -> {
                builder.setContentTitle(contentTitle)
                builder.setContentText(contentText)
                builder.setSubText(subText)
            }
            else -> {
                builder.setContentTitle(if (contentTitle.isEmpty()) null else contentTitle)
                builder.setContentText(if (contentText.isEmpty()) null else contentText)
                builder.setSubText(subText)
            }
        }

        return builder.build()
    }

    private fun createContentPendingIntent(): PendingIntent {
        return PendingIntent.getBroadcast(
            this,
            ACTION_REQUEST_CODE_OPEN_MENU,
            Intent(ACTION_OPEN_MENU),
            PendingIntent.FLAG_CANCEL_CURRENT
        )
    }

    private fun initChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel: NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.hype_notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = getString(R.string.hype_notification_channel_description)
            channel.enableLights(true)
            channel.lightColor = Color.BLUE
            channel.enableVibration(false)
            channel.importance = NotificationManager.IMPORTANCE_LOW
            notificationManager!!.createNotificationChannel(channel)
        }
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        registerReceiver(actionOpenMenuReceiver, IntentFilter(ACTION_OPEN_MENU))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(actionOpenMenuReceiver)
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class OpenMenuReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val activity: Activity? = this@HyperionService.activity?.get()
            if (activity != null) {
                AppComponent.Holder.getInstance(this@HyperionService)
                    .publicControl
                    .open(activity)
            }
        }

    }

    inner class Binder : android.os.Binder() {
        fun getService(): HyperionService {
            return this@HyperionService
        }
    }


    companion object {
        private val NOTIFICATION_ID = 0x400
        private val CHANNEL_ID = "hyperion-activation-channel"

        private val ACTION_REQUEST_CODE_OPEN_MENU = 0x100
        private val ACTION_OPEN_MENU = "open-menu"

    }

    @ActivityScope
    class Connection @Inject
    constructor(private val activity: Activity) : ServiceConnection {
        private var service: HyperionService? = null

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            this.service = (service as HyperionService.Binder).getService()
            this.service!!.attach(activity)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            this.service?.detach(activity)
            service = null
        }
    }
}