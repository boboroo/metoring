package com.mentoring.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mentoring.sample.data.datasource.LocalMainDataSource
import com.mentoring.sample.data.repository.MainRepository
import com.mentoring.sample.databinding.ViewRecyclerBinding
import com.mentoring.sample.ui.adapter.MainRecyclerAdapter
import com.mentoring.sample.ui.base.AbstractTitleFragment
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : AbstractTitleFragment<MainViewModel>() {

    lateinit var detailBinding : ViewRecyclerBinding

    @Inject
    lateinit var mainAdapter: MainRecyclerAdapter

    override val viewModel by activityViewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(MainRepository(LocalMainDataSource())) as T
            }
        }
    }

    companion object {
        fun newInstance() = DetailFragment()
    }

    override fun getBodyView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ViewRecyclerBinding.inflate(inflater, container, false).also { createdBinding ->
            detailBinding = createdBinding
            detailBinding.recyclerView.run {
                layoutManager = LinearLayoutManager(context)
                adapter = mainAdapter
            }
        }.root
    }

    override fun initViewModel() {
        viewModel.loadData()

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