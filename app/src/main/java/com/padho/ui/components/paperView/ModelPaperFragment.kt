package com.padho.ui.components.paperView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fdbanks.ui.base.BaseFragment
import com.padho.R
import com.padho.databinding.FragmentModelPaperBinding

class ModelPaperFragment : BaseFragment<FragmentModelPaperBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun observeViewModel() {
    }

    override fun getLayoutRes() = R.layout.fragment_model_paper

}