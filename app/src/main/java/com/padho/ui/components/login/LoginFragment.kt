package com.padho.ui.components.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.padho.R
import com.padho.databinding.FragmentLoginBinding
import com.fdbanks.ui.base.BaseFragment
import com.fdbanks.utils.ShowMessage
import com.padho.data.AuthState
import com.padho.data.Common
import com.padho.data.MyResults
import com.padho.ui.components.splash.SplashScreenFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun observeViewModel() {
    }

    val vm by viewModels<LoginVM>()
    override fun getLayoutRes() = R.layout.fragment_login
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginTV.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginToHome())

        }
    }

    override fun onResume() {
        super.onResume()
        baseActivity?.updateStatusBarColor(R.color.white, false, R.color.white)
    }
}