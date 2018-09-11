package com.cintory.hyperion.core.internal

import dagger.Lazy
import javax.inject.Provider


/**
 * Created by Cintory on 2018/8/29 17:51
 * Email：Cintory@gmail.com
 */
class Cached<T>(private val provider: Provider<T>) : Lazy<T> {

    private var item: T? = null

    override fun get(): T {
        if (item == null) {
            item = provider.get()
        }
        return item!!
    }

}