package com.mentoring.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import com.mentoring.sample.data.datasource.LocalMainDataSource
import com.mentoring.sample.data.repository.MainRepository
import com.mentoring.sample.databinding.FragmentProductsBinding
import com.mentoring.sample.ui.adapter.MainRecyclerAdapter
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
        super.onCreateView(inflater, container, savedInstanceState)
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
        viewModel.onItemClicked = OnClick { data ->
            activity?.run {
                if (!isFinishing()) {
                    val dialog = ProductDetailDialog.newInstance(data)
                    getSupportFragmentManager().beginTransaction()
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
                is MainViewModel.MainUiEvent.ShowProgress -> {
                }
                is MainViewModel.MainUiEvent.Success -> {
                    mainAdapter.setItems(uiEvent.data)
                }
                is MainViewModel.MainUiEvent.Error -> {
                    Toast.makeText(context, uiEvent.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}