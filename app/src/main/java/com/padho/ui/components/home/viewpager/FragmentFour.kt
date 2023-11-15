package com.padho.ui.components.home.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.padho.R
import com.padho.databinding.FragmentFourBinding
import com.padho.ui.components.home.viewpager.adapter.ProductAdapter
import com.padho.ui.components.home.viewpager.model.ProductModel
import com.padho.ui.components.home.viewpager.model.ProductVM
import com.fdbanks.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentFour: BaseFragment<FragmentFourBinding>(){
      override fun getLayoutRes()=R.layout.fragment_four
    override fun observeViewModel() {

    }
    var productAdpter: ProductAdapter? = null
    val vm by viewModels<ProductVM>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // productAdpter = ProductAdapter(ArrayList<ProductModel>()) {}
        //listInIt()
        //vm.setData(requireContext())
        //obsorve()
    }

    private fun obsorve() {
        GlobalScope.launch {
            vm.productData.collect {
                if (it.isloading) {
                    /// show error
                } else if (it.data != null) {
                    launch(Dispatchers.Main) {
                      //  productAdpter?.addItem(it.data as ArrayList<ProductModel>)
                       // binding.productRV.adapter = productAdpter
                    }
                } else {
                    //// show error
                }
            }
        }
    }


    private fun listInIt() {
        binding.productRV.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)

        }
    }}