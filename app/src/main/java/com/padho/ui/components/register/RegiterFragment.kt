package com.padho.ui.components.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padho.R
import com.padho.databinding.FragmentRegiterBinding
import com.fdbanks.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegiterFragment : BaseFragment<FragmentRegiterBinding>(){
    override fun observeViewModel() {
    }

    override fun getLayoutRes()= R.layout.fragment_regiter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
       baseActivity?.updateStatusBarColor(R.color.white,false,android.R.color.white)

    }
}