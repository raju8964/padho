package com.padho.ui.components.home.viewpager.model

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.padho.R
import com.padho.data.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductVM @Inject constructor() : ViewModel() {
    var _productLive = MutableStateFlow(AuthState())
    var productData: StateFlow<AuthState> = _productLive

    fun setData(context: Context) {
        viewModelScope.launch {

            var pTitle = context.resources.getStringArray(R.array.productName)
            var pImage = context.resources.obtainTypedArray(R.array.productImage)
          //  _productLive.value = AuthState(isloading = true)
            var list=ArrayList<ProductModel>()
            for (i in 0 until  pTitle.size)
            {
                var data = ProductModel()
                data.title = pTitle[i]
                data.icon=pImage.getDrawable(i)
                list.add(data)
            }
            _productLive.value=AuthState(data =list)
        }
    }
}