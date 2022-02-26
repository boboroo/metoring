package com.mentoring.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mentoring.sample.data.datasource.LocalMainDataSource
import com.mentoring.sample.data.repository.MainRepository
import com.mentoring.sample.databinding.FragmentProductsBinding
import com.mentoring.sample.ui.adapter.MainRecyclerAdapter
import com.mentoring.sample.ui.base.AbstractViewModel
import com.mentoring.sample.ui.dialogs.ProductDetailDialog
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment: Fragment() {
    lateinit var binding: FragmentProductsBinding

    lateinit var mainAdapter: MainRecyclerAdapter

    val viewModel by activityViewModels<MainViewModel> {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(MainRepository(LocalMainDataSource())) as T
            }
        }
    }

    companion object {
        fun newInstance() = ProductsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        initAdapter()
        initViewModel()
        return binding.root
    }


    fun initAdapter() {
        binding.recyclerView.run {
            adapter = MainRecyclerAdapter().also { createdAdapter ->
                mainAdapter = createdAdapter
                mainAdapter.setHasStableIds(true)
            }
        }
        viewModel.onItemClickListener = OnItemClickListener { data ->
            activity?.let { hostActivity ->
                if (!hostActivity.isFinishing()) {
                    val dialog = ProductDetailDialog.newInstance(data)
                    hostActivity.getSupportFragmentManager().beginTransaction()
                        .add(dialog, "DetailProductDialog")
                        .commitAllowingStateLoss()
                }
            }
        }
        viewModel.loadData()
    }


    fun initViewModel() {
        viewModel.uiData.observe(viewLifecycleOwner, Observer { uiEvent ->
            Logger.d("uiEvent")
            when(uiEvent) {
                is AbstractViewModel.UiEvent.ShowProgress -> {
                }
                is AbstractViewModel.UiEvent.Success -> {
                    mainAdapter.setItems(uiEvent.data)
                }
                is AbstractViewModel.UiEvent.Error -> {
                    Toast.makeText(context, uiEvent.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}