package com.padho.ui.components.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStarted
import androidx.navigation.fragment.findNavController
import com.padho.R
import com.padho.databinding.FragmentSplashScreenBinding
import com.padho.data.Common
import com.fdbanks.ui.base.BaseFragment
import com.fdbanks.utils.SharedPref
import com.fdbanks.utils.ShowMessage
import com.padho.ui.components.login.LoginFragmentDirections
import com.padho.ui.components.login.LoginVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>() {
    override fun observeViewModel() {
        CoroutineScope(Dispatchers.Main).launch {
            vm.loginData.collect {
                if (it.isloading) {
                    ///loading
                    progres.show()
                } else if (it.data != null) {
                    progres.hide()
                    findNavController().navigate(SplashScreenFragmentDirections.actionSplashFragmentToHome())
                    sharedPref?.setValue(Common.ISLOGIN, "true")
                } else {
                    /// show error
                    ShowMessage.message(requireContext(), it.isError)
                    progres.hide()
                }

            }
        }

    }

    val vm by viewModels<LoginVM>()

    override fun getLayoutRes() = R.layout.fragment_splash_screen
    var status = "false"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       ///  status = sharedPref?.getValue(Common.ISLOGIN)!!

        lifecycleScope.launch {
            delay(200)
            if (status.equals("true")) {
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashFragmentToHome())
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    vm.login()
                }

                // findNavController().navigate(SplashScreenFragmentDirections.actionSplashFragmentToWelcomeFragment())
            }
        }

    }

    override fun onResume() {
        super.onResume()
        baseActivity?.updateStatusBarColor(R.color.white, false, android.R.color.white)
    }


}