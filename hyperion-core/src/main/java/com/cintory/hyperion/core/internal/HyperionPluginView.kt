package com.cintory.hyperion.core.internal

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v4.view.WindowInsetsCompat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.cintory.hyperion.core.BuildConfig
import com.cintory.hyperion.core.R
import com.cintory.hyperion.plugin.v1.HyperionMenu
import com.cintory.hyperion.plugin.v1.MenuState
import com.cintory.hyperion.plugin.v1.PluginModule
import java.util.*
import javax.inject.Inject

/**
 * Created by Cintory on 2018/8/30 17:11
 * Email：Cintory@gmail.com
 */
class HyperionPluginView(context: Context) : FrameLayout(context) {

    private val pluginListContainer: LinearLayout
    private val pluginExtension: PluginExtensionImpl


    @Inject
    lateinit var pluginRepository: PluginRepository

    private var modules: Set<PluginModule>? = null
    private var menuState = MenuState.CLOSE

    init {

        var component = ComponentProvider.get(context)
        component.inject(this)

        inflate(context, R.layout.hype_view_plugin, this)
        val versionText: TextView = findViewById(R.id.version_text)
        versionText.text = context.getString(R.string.hype_version_text, BuildConfig.VERSION_NAME)

        pluginListContainer = findViewById(R.id.plugin_list_container)
        pluginExtension = PluginExtensionImpl(component)
        fitsSystemWindows = true
        id = R.id.hyperion_plugins
        ViewCompat.setImportantForAccessibility(this, ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO)
        ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
            setPadding(
                insets.systemWindowInsetLeft,
                insets.systemWindowInsetTop,
                insets.systemWindowInsetRight,
                insets.systemWindowInsetBottom
            )
            insets
        }
    }

    fun setMenuState(menuState: MenuState) {
        this.menuState = menuState
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // block touches while the menu is closed.
        return menuState == menuState || super.onInterceptTouchEvent(ev)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        pluginExtension.setHyperionMenu(parent as HyperionMenu)
        populatePluginList(pluginRepository.getPlugins())
    }

    fun populatePluginList(plugins: Plugins) {
        val comparator = AlphabeticalComparator(context)
        val sortedModules = TreeSet<PluginModule>(comparator)
        sortedModules.addAll(plugins.createModules())
        this.modules = sortedModules

        val context = PluginExtensionContextWrapper(context, pluginExtension)
        val inflater = LayoutInflater.from(context)

        for (module in modules!!) {
            module.create(pluginExtension, context)
            val view = module.createPluginView(inflater, pluginListContainer)
            pluginListContainer.addView(view)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (modules != null) {
            for (module in modules!!) {
                module.destory()
            }
        }
        pluginExtension.setHyperionMenu(null)
    }

    companion object {
        private class AlphabeticalComparator(private val context: Context) :
            Comparator<PluginModule> {

            override fun compare(left: PluginModule?, right: PluginModule?): Int {
                var leftName = getName(left!!)
                val rightName = getName(right!!)
                return leftName.compareTo(rightName)
            }

            private fun getName(pluginModule: PluginModule): String {
                var resName = pluginModule.getName()
                if (resName == R.string.hype_module_name) return pluginModule.javaClass.simpleName
                else return context.getString(resName)
            }
        }
    }
}