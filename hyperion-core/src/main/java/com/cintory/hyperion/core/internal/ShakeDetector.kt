package com.cintory.hyperion.core.internal

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

/**
 * Created by Cintory on 2018/9/3 15:48
 * Email：Cintory@gmail.com
 */

/**
 *  Detects a shake to open the side drawer.
 */
class ShakeDetector : SensorEventListener {

    private var listener: OnShakeListener? = null
    private var shakeTimestamp: Long = 0L

    fun setShakeGestureSensitivity(sensitivity: Float) {
        shakeThresholdGravity = sensitivity

    }

    fun setOnShakeListener(listener: OnShakeListener) {
        this.listener = listener
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        listener ?: return
        event ?: return

        var x = event.values[0]
        var y = event.values[1]
        var z = event.values[2]

        var gX = (x / SensorManager.GRAVITY_EARTH).toDouble()
        var gY = (y / SensorManager.GRAVITY_EARTH).toDouble()
        var gZ = (z / SensorManager.GRAVITY_EARTH).toDouble()

        //gForce will be close to 1 when there is no movement
        var gForce = Math.sqrt(gX * gX + gY * gY + gZ + gZ).toFloat()

        if (gForce > shakeThresholdGravity) {
            val now = System.currentTimeMillis()
            if (shakeTimestamp > SHAKE_SPACING_TIME_MS + now) {
                return
            }

            shakeTimestamp = now
            listener!!.onShake()
        }
    }


    interface OnShakeListener {
        fun onShake()
    }

    companion object {
        private var shakeThresholdGravity = 3.0F
        private val SHAKE_SPACING_TIME_MS = 500
    }
}
