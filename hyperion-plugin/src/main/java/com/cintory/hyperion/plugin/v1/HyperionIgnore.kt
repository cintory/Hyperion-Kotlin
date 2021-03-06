package com.cintory.hyperion.plugin.v1


/**
 * Created by Cintory on 2018/9/3 13:54
 * Email：Cintory@gmail.com
 */

/**
 * Annotate {@link android.app.Activity} subclasses with this to prevent Hyperion form embedding
 * itself in its layout.
 *
 * This can be useful for plugins that wish to start their own {@link android.app.Activity}
 * subclass as part of their functionality.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class HyperionIgnore