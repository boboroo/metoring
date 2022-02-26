package com.mentoring.sample.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mentoring.sample.R
import com.mentoring.sample.data.datasource.LocalMainDataSource
import com.mentoring.sample.data.repository.MainRepository
import com.mentoring.sample.databinding.FragmentProductsBinding
import com.mentoring.sample.ui.adapter.MainRecyclerAdapter
import com.mentoring.sample.ui.base.AbstractPersistentFragment
import com.mentoring.sample.ui.base.AbstractViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment: AbstractPersistentFragment<FragmentProductsBinding, MainViewModel>() {

    private lateinit var mainAdapter: MainRecyclerAdapter

    override val viewModel by activityViewModels<MainViewModel> {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(MainRepository(LocalMainDataSource())) as T
            }
        }
    }

    companion object {
        const val ARG_PRODUCT_DETAIL_CONTAINER_ID_KEY = "productDetailFragmentContainer"

        fun newInstance(detailFragmentContainerId: Int) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PRODUCT_DETAIL_CONTAINER_ID_KEY, detailFragmentContainerId)
            }
        }
    }


    override fun getResourceId(): Int {
        return R.layout.fragment_products
    }


    override fun initView(root: View) {
        binding.recyclerView.run {
            adapter = MainRecyclerAdapter().also { createdAdapter ->
                mainAdapter = createdAdapter
                mainAdapter.setHasStableIds(true)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
        initViewModel()
    }


    override fun initViewModel() {
        arguments?.getInt(ARG_PRODUCT_DETAIL_CONTAINER_ID_KEY)?.let { productDetailContainerId ->
            viewModel.onItemClickListener = OnItemClickListener { data ->
                activity?.let { hostActivity ->
                    if (!hostActivity.isFinishing()) {
                        val fragment = ProductDetailFragment.newInstance(data)
                        hostActivity.getSupportFragmentManager().beginTransaction()
                            .add(productDetailContainerId, fragment)
                            .addToBackStack(fragment.tag)
                            .commitAllowingStateLoss()
                    }
                }
            }
        }

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
