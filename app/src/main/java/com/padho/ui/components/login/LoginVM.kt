package com.padho.ui.components.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padho.data.AuthState
import com.padho.data.Common
import com.padho.firebase.FBRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(var fbrepo:FBRepo) : ViewModel() {
    var _loginData = MutableStateFlow(AuthState())
    var loginData: StateFlow<AuthState> = _loginData

    fun login() {
        viewModelScope.launch {
            _loginData.value = AuthState(isloading = true)
            try {
                fbrepo.signInWithEmailAndPassword {
                    if (it.equals(Common.loginSuccess)) {
                        _loginData.value = AuthState(data = it)
                    } else {
                        _loginData.value = AuthState(error(it))
                    }
                }
            } catch (e: Exception) {
                _loginData.value = AuthState(error(e.toString()))
            }

        }
    }

}