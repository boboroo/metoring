package com.mentoring.sample.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mentoring.sample.data.models.UIData
import com.mentoring.sample.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailDialog: DialogFragment() {
    lateinit var binding : FragmentProductDetailBinding

    companion object {
        const val ARG_UI_DATA_KEY = "uiData"

        fun newInstance(data: UIData) = ProductDetailDialog().apply {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_NoTitleBar_Fullscreen);
            arguments = Bundle().apply {
                putParcelable(ARG_UI_DATA_KEY, data)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentProductDetailBinding.inflate(inflater, container).also { createdBinding ->
            binding = createdBinding
            arguments?.getParcelable<UIData>(ARG_UI_DATA_KEY)?.also { uiData ->
                binding.model = uiData
            }
            binding.btnClose.setOnClickListener { dismissAllowingStateLoss() }
        }.root
    }

}