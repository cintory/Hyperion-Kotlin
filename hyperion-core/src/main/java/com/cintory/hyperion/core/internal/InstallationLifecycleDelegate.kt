package com.cintory.hyperion.core.internal

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.cintory.hyperion.plugin.v1.ActivityResults
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/3 15:06
 * Email：Cintory@gmail.com
 */
@AppScope
class InstallationLifecycleDelegate @Inject constructor(
    private val container: CoreComponentContainer,
    private val applicationInstaller: ApplicationInstaller
) : LifecycleDelegate() {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        applicationInstaller.installIfNeed()

        //  reorganize the layout
        val decorView = activity.window.decorView as ViewGroup
        val contentView = decorView.getChildAt(0)
        if (decorView.childCount < 1) {
            //  no content, abort install
            return
        }
        decorView.removeView(contentView)

        //  embed content view within overlay
        val overlayLayout = HyperionOverlayLayout(activity as Context)
        overlayLayout.addView(contentView)

        //  embed overlay + content within menu
        val menuLayout = HyperionMenuLayout(activity)
        decorView.addView(menuLayout)

        var fragmentManager = FragmentManagerCompat.create(activity)

        var activityResults =
            fragmentManager.findFragmentByTag<ActivityResults?>(ACTIVITY_RESULT_TAG)
        if (activityResults == null) {
            activityResults =
                    if (fragmentManager.isSupport()) ActivityResultsSupportFragment() else ActivityResultsFragment()
            fragmentManager.beginTransaction()
                .add(activityResults, ACTIVITY_RESULT_TAG)
                .commit()
        }

        val component = DaggerCoreComponent.builder()
            .appComponent(AppComponent.Holder.getInstance(activity))
            .activity(activity)
            .pluginSource(container.pluginSource)
            .overlayContainer(overlayLayout)
            .activityResults(activityResults)
            .build()

        container.putComponent(activity, component)

        // embed plugins list into menu
        val coreContext = ComponentContextThemeWrapper(activity, component)
        val pluginView = HyperionPluginView(coreContext)
        pluginView.alpha = 0.0f
        menuLayout.addView(pluginView)
        menuLayout.addView(overlayLayout)

    }

    override fun onActivityDestroyed(activity: Activity) {
        container.removeComponent(activity)
    }

    companion object {
        private val ACTIVITY_RESULT_TAG = "hyperion_activity_result"
    }
}