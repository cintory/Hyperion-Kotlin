package com.cintory.hyperion.sample

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager

/**
 * Created by Cintory on 2018/9/5 15:05
 * Email：Cintory@gmail.com
 */
class PagerFragment : Fragment() {

    private lateinit var pager: ViewPager
    private lateinit var adapter: MainAdapter

    private class MainAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return MainFragment()
                1 -> return GestureFragment()
                2 -> return VersionFragment()
            }
            return null
        }
    }
}