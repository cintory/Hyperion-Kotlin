package com.cintory.hyperion.core.internal

import com.cintory.hyperion.plugin.v1.ApplicationExtension
import com.cintory.hyperion.plugin.v1.ForegroundManager
import javax.inject.Inject

/**
 * Created by Cintory on 2018/9/4 15:46
 * Email：Cintory@gmail.com
 */
@AppScope
class ApplicationExtensionImpl @Inject constructor(private val foregroundManager: ForegroundManager) :
    ApplicationExtension {

    override fun getForegroundManager(): ForegroundManager {
        return foregroundManager
    }

}