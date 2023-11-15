package com.padho.ui.components.home.viewpager.adapter

import com.padho.R
import com.padho.databinding.ProductRecyclerviewBinding
import com.padho.ui.components.home.viewpager.model.ProductModel

import com.fdbanks.ui.base.BaseAdapter
import com.fdbanks.ui.base.BaseViewHolder
import com.fdbanks.utils.loadImage
import com.padho.ui.components.paperView.PaperDialog

class ProductAdapter(var list:ArrayList<String>,var callBack: (String) -> Unit) :
    BaseAdapter<ProductRecyclerviewBinding>() {

    override fun getLayoutRes(): Int = R.layout.product_recyclerview

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (list.size > 0) {
            var data = list.get(position)
            binding.apply {
               // productIV.setImageDrawable(data.icon)
                productTitleTV.setText(data)
                holder.itemView.setOnClickListener {
                    callBack.invoke(data)
                }
            }
        }
    }

    fun addItem(data: ArrayList<String>) {
//        if (list.size > 0) {
//            list.clear()
//        }
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }
}