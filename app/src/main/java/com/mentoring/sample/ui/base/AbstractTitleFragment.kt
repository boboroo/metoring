package com.mentoring.sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentoring.sample.databinding.FragmentTitleBodyBinding
import com.mentoring.sample.ui.MainViewModel

abstract class AbstractTitleFragment<VM: MainViewModel> : Fragment() {

    abstract val viewModel: VM

    private lateinit var binding: FragmentTitleBodyBinding
    abstract fun getBodyView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?
    abstract fun initViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTitleBodyBinding.inflate(inflater, container, false).also { createdBinding ->
            binding = createdBinding
            getBodyView(inflater, container, savedInstanceState)?.also { createdBodyView ->
                binding.layoutBody.addView(createdBodyView)
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

        viewModel.progressBar.observe(viewLifecycleOwner, { progressBarShow ->
            binding.progress.visibility = if(progressBarShow) View.VISIBLE else View.GONE
        })

//        viewModel.팝업.observe(viewLifecycleOwner, { progressBarShow ->
//            binding.progress.visibility = if(progressBarShow) View.VISIBLE else View.GONE
//        })
//
//        viewModel.토스트.observe(viewLifecycleOwner, { progressBarShow ->
//            binding.progress.visibility = if(progressBarShow) View.VISIBLE else View.GONE
//        })
    }
}