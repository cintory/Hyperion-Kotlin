package com.cintory.hyperion.core.internal

import javax.inject.Scope

/**
 * Created by Cintory on 2018/8/29 11:29
 * Email：Cintory@gmail.com
 */
@Scope
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope