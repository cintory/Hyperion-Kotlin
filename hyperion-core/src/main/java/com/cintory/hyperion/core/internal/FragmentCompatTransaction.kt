package com.cintory.hyperion.core.internal

import android.support.annotation.IdRes

/**
 * Created by Cintory on 2018/9/3 19:30
 * Email：Cintory@gmail.com
 */
abstract class FragmentCompatTransaction {

    abstract fun add(fragment: Any, tag: String?): FragmentCompatTransaction

    abstract fun add(
        @IdRes containerViewId: Int,
        fragment: Any,
        tag: String?
    ): FragmentCompatTransaction

    abstract fun remove(fragment: Any): FragmentCompatTransaction

    abstract fun commit()

    companion object {
        fun beginTransaction(transaction: android.app.FragmentTransaction):
                FragmentCompatTransaction {
            return Android(transaction)
        }

        fun beginTransaction(transaction: android.support.v4.app.FragmentTransaction):
                FragmentCompatTransaction {
            return Support(transaction)
        }
    }

    private class Android(private val transaction: android.app.FragmentTransaction) :
        FragmentCompatTransaction() {
        override fun add(fragment: Any, tag: String?): FragmentCompatTransaction {
            transaction.add(fragment as android.app.Fragment, tag)
            return this
        }

        override fun add(
            containerViewId: Int,
            fragment: Any,
            tag: String?
        ): FragmentCompatTransaction {
            transaction.add(containerViewId, fragment as android.app.Fragment, tag)
            return this
        }

        override fun remove(fragment: Any): FragmentCompatTransaction {
            transaction.remove(fragment as android.app.Fragment)
            return this
        }

        override fun commit() {
            transaction.commit()
        }
    }

    private class Support(private val transaction: android.support.v4.app.FragmentTransaction) :
        FragmentCompatTransaction() {

        override fun add(fragment: Any, tag: String?): FragmentCompatTransaction {
            transaction.add(fragment as android.support.v4.app.Fragment, tag)
            return this
        }

        override fun add(
            containerViewId: Int,
            fragment: Any,
            tag: String?
        ): FragmentCompatTransaction {
            transaction.add(containerViewId, fragment as android.support.v4.app.Fragment, tag)
            return this
        }

        override fun remove(fragment: Any): FragmentCompatTransaction {
            transaction.remove(fragment as android.support.v4.app.Fragment)
            return this
        }

        override fun commit() {
            transaction.commit()
        }

    }
}