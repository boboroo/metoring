package com.mentoring.sample.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class AbstractBindingFragment<B: ViewDataBinding, VM: ViewModel> : Fragment() {

    lateinit var binding: B

    abstract val viewModel: VM

    abstract fun getResourceId() : Int

    abstract fun initView(root: View)

    abstract fun initViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<B>(inflater, getResourceId(), container, false)
        initView(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }
}