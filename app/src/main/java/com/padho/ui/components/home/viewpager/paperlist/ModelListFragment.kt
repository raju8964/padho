package com.padho.ui.components.home.viewpager.paperlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.padho.R
import com.padho.ui.components.home.viewpager.adapter.ProductAdapter
import com.padho.ui.components.home.viewpager.model.ProductModel
import com.padho.ui.components.home.viewpager.model.ProductVM
import com.fdbanks.ui.base.BaseFragment
import com.fdbanks.utils.ShowMessage
import com.padho.databinding.ModellistFragmentBinding
import com.padho.ui.components.home.viewpager.paperlist.data.viewModel.PaperListVM
import com.padho.ui.components.paperView.PaperDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ModelListFragment : BaseFragment<ModellistFragmentBinding>() {
    override fun observeViewModel() {
    }

    var productAdpter: ProductAdapter? = null
    val vm by viewModels<PaperListVM>()
    override fun getLayoutRes() = R.layout.modellist_fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productAdpter = ProductAdapter(ArrayList()) {
            PaperDialog(vm,requireContext(),it).showDialog()
        }
        listInIt()
        vm.setData()
/////
        obsorve()
    }

    private fun obsorve() {
        GlobalScope.launch {
            vm.paperListData.collect {
                if (it.isloading) {
                    showProgress()
                } else if (it.data != null) {
                    dismissProgress()
                    launch(Dispatchers.Main) {
                        productAdpter?.addItem(it.data as ArrayList<String>)
                        binding.productRV.adapter = productAdpter
                    }
                } else {
                    //// show error
                    ShowMessage.message(requireContext(), it.isError)
                    dismissProgress()
                }
            }
        }
    }

    private fun listInIt() {
        binding.productRV.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 3)

        }
    }
}