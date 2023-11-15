package com.padho.ui.components.home.viewpager.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.padho.ui.components.home.viewpager.FragmentFour
import com.padho.ui.components.home.viewpager.paperlist.ModelListFragment
import com.padho.ui.components.home.viewpager.FragmentThree
import com.padho.ui.components.home.viewpager.FragmentTwo


class PagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ModelListFragment()
            1 -> FragmentTwo()
            2 -> FragmentThree()
            3 -> FragmentFour()
            else -> {
                ModelListFragment()
            }
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

    companion object {
        private const val NUM_ITEMS = 4
    }
}
