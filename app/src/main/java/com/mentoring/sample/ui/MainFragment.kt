package com.mentoring.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mentoring.sample.R
import com.mentoring.sample.data.datasource.LocalMainDataSource
import com.mentoring.sample.data.repository.MainRepository
import com.mentoring.sample.databinding.FragmentMainBinding
import com.mentoring.sample.ui.adapter.MainRecyclerAdapter
import com.mentoring.sample.ui.base.AbstractBindingFragment
import com.mentoring.sample.util.ex.EventObserver
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : AbstractBindingFragment<FragmentMainBinding, MainViewModel>() {

    private lateinit var mainAdapter: MainRecyclerAdapter

    override val viewModel by viewModels<MainViewModel> {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel("", MainRepository(LocalMainDataSource())) as T
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun getResourceId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(root: View) {
        mainAdapter = MainRecyclerAdapter()

        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = MainRecyclerAdapter().also { createdAdapter ->
                mainAdapter = createdAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
    }

    override fun initViewModel() {

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