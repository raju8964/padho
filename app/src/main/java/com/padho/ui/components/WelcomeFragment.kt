package com.padho.ui.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.padho.R
import com.padho.databinding.FragmentWelcomeBinding
import com.fdbanks.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment: BaseFragment<FragmentWelcomeBinding>(){
    override fun observeViewModel() {

    }

    override fun getLayoutRes()=R.layout.fragment_welcome
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            loginTV.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomToLoginFragment())
            }
            signupTV.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeToSignupFragment())
            }
        }
    }
    override fun onResume() {
        super.onResume()
        baseActivity?.updateStatusBarColor(R.color.white,false,android.R.color.white)

    }

}