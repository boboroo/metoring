package com.mentoring.sample.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mentoring.sample.R
import com.mentoring.sample.databinding.FragmentRecentlyProductsBinding
import com.mentoring.sample.ui.adapter.RecentlyProductsRecyclerAdapter
import com.mentoring.sample.ui.base.AbstractPersistentFragment
import com.mentoring.sample.ui.base.AbstractViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentlyProductsFragment: AbstractPersistentFragment<FragmentRecentlyProductsBinding, RecentlyProductsViewModel>() {

    private lateinit var recyclerViewAdapter: RecentlyProductsRecyclerAdapter

    override val viewModel: RecentlyProductsViewModel by viewModels()

    companion object {
        fun newInstance() = RecentlyProductsFragment()
    }


    override fun getResourceId(): Int {
        return R.layout.fragment_recently_products
    }


    override fun initView(root: View) {
        binding.recyclerView.run {
            adapter = RecentlyProductsRecyclerAdapter().also { createdAdapter ->
                recyclerViewAdapter = createdAdapter
                recyclerViewAdapter.setHasStableIds(true)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
        initViewModel()
    }


    override fun initViewModel() {
        viewModel.uiData.observe(viewLifecycleOwner, Observer { uiEvent ->
            Logger.d("uiEvent")
            when(uiEvent) {
                is AbstractViewModel.UiEvent.ShowProgress -> {
                }
                is AbstractViewModel.UiEvent.Success -> {
                    recyclerViewAdapter.setItems(uiEvent.data)

                    if (uiEvent.data.size > 0) {
                        binding.txtNoData.visibility = View.GONE
                    }
                    else {
                        binding.txtNoData.visibility = View.VISIBLE
                    }
                }
                is AbstractViewModel.UiEvent.Error -> {
                    Toast.makeText(context, uiEvent.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}