package com.padho.ui.components.home.viewpager.paperlist.data.viewModel

import com.padho.ui.components.home.viewpager.model.ProductModel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.core.Repo
import com.padho.R
import com.padho.data.AuthState
import com.padho.firebase.FBRepo
import com.padho.ui.components.paperView.data.PaperModel
import com.padho.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PaperListVM @Inject constructor(var repo: FBRepo) : ViewModel() {
    var _paperListLive = MutableStateFlow(AuthState())
    var paperListData: StateFlow<AuthState> = _paperListLive
/// papers
    var _paper= MutableStateFlow(AuthState())
    var paperData:StateFlow<AuthState> = _paper
    fun setData() {
        viewModelScope.launch {
            _paperListLive.value = AuthState(isloading = true)
            repo.getModelPaper { data, isSuccess ->
                if (isSuccess) {
                    _paperListLive.value = AuthState(data = data as ArrayList<String>)
                } else {
                    _paperListLive.value = AuthState(error("Something Went wrong"))
                }
            }

        }
    }
    fun getPaper(path:String)
        {
            viewModelScope.launch {
                _paper.value = AuthState(isloading = true)
                repo.getPaper(path) { data, isSuccess ->
                    if (isSuccess) {
                        _paper.value = AuthState(data = data as ArrayList<PaperModel>)
                    } else {
                        _paper.value = AuthState(error("Something Went wrong"))
                    }
                }

            }
        }

}