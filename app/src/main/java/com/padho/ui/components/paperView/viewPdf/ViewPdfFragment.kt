package com.padho.ui.components.paperView.viewPdf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fdbanks.ui.base.BaseFragment
import com.padho.R
import com.padho.databinding.FragmentViewPdfBinding

class ViewPdfFragment : BaseFragment<FragmentViewPdfBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // displayPDFFromUrl(pdfUrl)
    }

    override fun observeViewModel() {

    }
   // private lateinit var pdfView: PDFView

    private fun displayPDFFromUrl(pdfUrl: String) {
//        pdfView.fromUri(pdfUrl)
//            .defaultPage(0)
//            .enableSwipe(true)
//            .swipeHorizontal(false)
//            .enableAnnotationRendering(true)
//            .pageFitPolicy(FitPolicy.BOTH)
//            .load()
    }
    override fun getLayoutRes()=R.layout.fragment_view_pdf
}