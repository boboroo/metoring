package com.mentoring.sample.ui

import android.os.Bundle
import android.view.View
import com.mentoring.sample.R
import com.mentoring.sample.data.models.UIData
import com.mentoring.sample.databinding.FragmentProductDetailBinding
import com.mentoring.sample.ui.base.AbstractBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment() : AbstractBindingFragment<FragmentProductDetailBinding>() {

    companion object {
        const val ARG_UI_DATA_KEY = "uiData"

        fun newInstance(data: UIData) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_UI_DATA_KEY, data)
            }
        }
    }


    override fun getResourceId(): Int {
        return R.layout.fragment_product_detail
    }


    override fun initView(root: View) {
        arguments?.getParcelable<UIData>(ARG_UI_DATA_KEY)?.also { uiData ->
            binding.model = uiData
        }
        binding.btnClose.setOnClickListener {
            activity?.let { hostActivity ->
                hostActivity.supportFragmentManager.beginTransaction()
                    .remove(this)
                    .commitAllowingStateLoss()
            }
        }
    }

}