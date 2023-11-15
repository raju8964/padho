package com.padho.ui.components.paperView

import com.fdbanks.ui.base.BaseAdapter
import com.fdbanks.ui.base.BaseViewHolder
import com.padho.R
import com.padho.databinding.PaperDialogRecyclervviewBinding
import com.padho.ui.components.paperView.data.PaperModel

class DialogPaperAdapter(var list: ArrayList<PaperModel>,val callBack:(Int)->Unit):
    BaseAdapter<PaperDialogRecyclervviewBinding>() {
    override fun getLayoutRes()= R.layout.paper_dialog_recyclervview

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        binding.apply {
            titleTV.setText(list[position].title)
            titleTV.setOnClickListener {
                callBack.invoke(position)
            }
        }
    }

    override fun getItemCount()=list.size
}