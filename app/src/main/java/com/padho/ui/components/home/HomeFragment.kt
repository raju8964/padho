package com.padho.ui.components.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.padho.R
import com.padho.databinding.FragmentHomeBinding
import com.padho.ui.components.home.viewpager.adapter.PagerAdapter
import com.fdbanks.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun observeViewModel() {
    }

    override fun getLayoutRes() = R.layout.fragment_home
    override fun onResume() {
        super.onResume()
        baseActivity?.updateStatusBarColor(R.color.white, false, R.color.light_blue)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setStatusColor(R.color.light_blue)


        binding.apply {
            var pagerAdapter = PagerAdapter(childFragmentManager)
            pager.adapter = pagerAdapter
            // dotsIndicator.setViewPager(pager)
            tabLayout.setupWithViewPager(pager, true);
            backwordIV.setOnClickListener {
                if (pager.currentItem > 0) {
                    pager.setCurrentItem(pager.currentItem - 1, true)
                }
            }

            forwardIV.setOnClickListener {
                pager.setCurrentItem(pager.currentItem + 1, true)
            }
            pager.addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageSelected(position: Int) {
                    tabLayout.setupWithViewPager(pager, true);
                    if (position==0){
                        viewTitleTV.setText("All Papers")
                    }else{
                        viewTitleTV.setText("Tools")
                    }
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    // Code here
                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })

        }


    }


}