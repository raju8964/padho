package com.padho.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<B : ViewBinding>(context: Context) : Dialog(context) {

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding()
        setContentView(binding.root)
        setup()
    }

    abstract fun createBinding(): B

    abstract fun setup()
}


