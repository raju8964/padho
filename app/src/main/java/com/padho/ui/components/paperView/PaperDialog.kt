package com.padho.ui.components.paperView

import android.content.Context
import android.view.LayoutInflater
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fdbanks.utils.ShowMessage
import com.padho.R
import com.padho.databinding.PaperDialogViewBinding
import com.padho.ui.base.BaseDialog
import com.padho.ui.components.home.viewpager.paperlist.data.viewModel.PaperListVM
import com.padho.ui.components.paperView.data.PaperModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaperDialog(var vm: PaperListVM, context: Context, val title: String) :
    BaseDialog<PaperDialogViewBinding>(context) {
    var dialogAdapter: DialogPaperAdapter? = null
    override fun createBinding() = PaperDialogViewBinding.inflate(LayoutInflater.from(context))
    var subList = ArrayList<PaperModel>()
    override fun setup() {
        vm.getPaper(title)
        CoroutineScope(Dispatchers.IO).launch {
            getList()
        }
        binding.paperDialogRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            dialogAdapter = DialogPaperAdapter(subList) {
                findNavController().navigate(R.id.viewPdfFragment)
            }
            adapter = dialogAdapter
        }
    }

    fun showDialog() {
        show()
    }

    fun dismissDialog() {
        CoroutineScope(Dispatchers.Main).launch {
            show()
        }
    }

    suspend fun getList() {

        subList = vm.paperData.collect {
            if (it.isloading) {
                dismissDialog()
            } else if (it.data != null) {
                CoroutineScope(Dispatchers.Main).launch {
                    subList.clear()
                    subList.addAll(it.data as ArrayList<PaperModel>)
                    dialogAdapter = DialogPaperAdapter(subList) {
                    }
                    binding.paperDialogRV.adapter = dialogAdapter
                }
            } else {
                //// show error
                ShowMessage.message(context, it.isError)
                dismissDialog()
            }
        }
    }
}