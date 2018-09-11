package com.cintory.hyperion.core.internal

import android.animation.ObjectAnimator
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.MarginLayoutParamsCompat
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.cintory.hyperion.core.R
import com.cintory.hyperion.plugin.v1.HyperionMenu
import com.cintory.hyperion.plugin.v1.MenuState
import com.cintory.hyperion.plugin.v1.OnMenuStateChangedListener
import java.util.*

/**
 * Created by Cintory on 2018/9/3 15:45
 * Email：Cintory@gmail.com
 */
class HyperionMenuLayout(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle),
    ShakeDetector.OnShakeListener,
    HyperionMenu {

    private val sensorManager: SensorManager
    private val accelerometer: Sensor
    private val shakeDetector: ShakeDetector
    private val listeners = ArrayList<OnMenuStateChangedListener>(4)

    private var pluginView: HyperionPluginView? = null
    private var overlayView: View? = null
    private var menuState = MenuState.CLOSE

    init {
        sensorManager = getContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        shakeDetector = ShakeDetector()
        shakeDetector.setOnShakeListener(this)
        setBackgroundColor(ContextCompat.getColor(context, R.color.hype_menu_background))
        background.alpha = 0
        isFocusable = false
        isFocusableInTouchMode = false
        id = R.id.hyperion_menu
        ViewCompat.setImportantForAccessibility(this, ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val pluginView = getPluginView()
        val params = pluginView.layoutParams as MarginLayoutParams
        val offset = ((measuredWidth * 0.8f) / 3f).toInt()
        MarginLayoutParamsCompat.setMarginStart(params, offset)
        pluginView.layoutParams = params
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        sensorManager.unregisterListener(shakeDetector)
    }

    fun setShakeGestureSensitivity(sensitivity: Float) {
        shakeDetector.setShakeGestureSensitivity(sensitivity)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && isMenuOpen()) {
            collapse()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val offset = ((measuredWidth * 0.8f) / 3f).toInt()
        val x = ev!!.x
        // menu is open and the user is pressing the app content area
        return (x < offset && isMenuOpen()) || super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.action == MotionEvent.ACTION_DOWN) {
            collapse()
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onShake() {
        expand()
    }

    fun expand() {
        if (!isMenuOpen()) {
            setMenuState(MenuState.OPENING)
            val pluginview = getPluginView()
            val overlayView = getOverlayView()
            pluginview.translationX = 160.0f
            val width = measuredWidth
            var offset = width - (width / 3)
            ViewCompat.animate(overlayView)
                .translationX(-offset.toFloat())
                .scaleX(0.8f)
                .scaleY(0.8f)
                .translationZ(10.0f)
                .setInterpolator(EXPAND_COLLAPSE_INTERPOLATOR)
                .setListener(object : ViewPropertyAnimatorListenerAdapter() {
                    override fun onAnimationEnd(view: View?) {
                        //  request focus so we can respond to key presses.
                        isFocusable = true
                        isFocusableInTouchMode = true
                        requestFocus()
                        setMenuState(MenuState.OPEN)
                    }
                })
                .start()
            ViewCompat.animate(pluginview)
                .withLayer()
                .alpha(1.0f)
                .translationX(0.0f)
                .setInterpolator(EXPAND_COLLAPSE_INTERPOLATOR)
                .start()
            var alpha = ObjectAnimator.ofInt(this, BACKGROUND_ALPHA, 255)
            alpha.setInterpolator(EXPAND_COLLAPSE_INTERPOLATOR)
            alpha.start()

            // This allows app who have backgrounds set on the window to look correct when we push
            // their content away.
            // Basically, we 'fake' their window background onto our overlay container before
            // animating it out to the left, so it appears the window background has remained
            // with the content.
            // Then we animate in our plugin menu background over top of their ACTUAL window
            // background.
            var activity = ActivityUtil.findActivity(this)
            if (activity != null) {
                var window = activity.window
                var decor = window.decorView
                ViewCompat.setBackground(overlayView, decor.background)
            }
        }
    }


    fun collapse() {
        if (isMenuOpen()) {
            setMenuState(MenuState.CLOSING)
            val pluginView = getPluginView()
            val overlayView = getOverlayView()
            ViewCompat.animate(overlayView)
                .translationX(0f)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .translationZ(0.0f)
                .setInterpolator(EXPAND_COLLAPSE_INTERPOLATOR)
                .setListener(object : ViewPropertyAnimatorListenerAdapter() {
                    override fun onAnimationEnd(view: View?) {
                        ViewCompat.setBackground(overlayView, null)
                        //  don't need to respond to key presses while we're closed, so clear it
                        isFocusable = false
                        isFocusableInTouchMode = false
                        clearFocus()
                        setMenuState(MenuState.CLOSE)
                    }
                })
                .start()
            ViewCompat.animate(pluginView)
                .withLayer()
                .alpha(0.0f)
                .translationX(160.0f)
                .setInterpolator(EXPAND_COLLAPSE_INTERPOLATOR)
                .start()
            var alpha = ObjectAnimator.ofInt(this, BACKGROUND_ALPHA, 0)
            alpha.interpolator = EXPAND_COLLAPSE_INTERPOLATOR
            alpha.start()

        }
    }

    private fun getPluginView(): HyperionPluginView {
        return pluginView ?: findViewById(R.id.hyperion_plugins)
    }

    private fun getOverlayView(): View {
        return overlayView ?: findViewById(R.id.hyperion_overlay)
    }

    private fun isMenuOpen(): Boolean {
        return menuState == MenuState.OPEN
    }

    override fun getMenuState(): MenuState {
        return menuState
    }

    override fun setMenuState(menuState: MenuState) {
        if (this.menuState != menuState) {
            this.menuState = menuState
            getPluginView().setMenuState(menuState)
            listeners.forEach { it.onMenuStateChanged(menuState) }
        }
    }

    override fun addOnMenuStateChangedListener(listener: OnMenuStateChangedListener) {
        listeners.add(listener)
    }

    companion object {
        private val EXPAND_COLLAPSE_INTERPOLATOR = FastOutSlowInInterpolator()
        private val BACKGROUND_ALPHA = object : IntProperty<HyperionMenuLayout>("BackgroundAlpha") {
            override fun setValue(layout: HyperionMenuLayout, value: Int) {
                layout.background.alpha = value
            }

            override fun get(layout: HyperionMenuLayout?): Int {
                return DrawableCompat.getAlpha(layout!!.background)
            }
        }
    }
}