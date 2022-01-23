package com.mentoring.sample.ui.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mentoring.sample.databinding.FragmentMainBinding
import com.mentoring.sample.ui.adapter.MainRecyclerAdapter
import com.mentoring.sample.util.dummy.DummyContents
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment2 : Fragment(), MainContract.View {

    lateinit var presenter : MainContract.Presenter

    private var binding: FragmentMainBinding?= null  // no lateInit
    private lateinit var mainAdapter: MainRecyclerAdapter

    companion object {
        fun newInstance() = MainFragment2()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMainBinding.inflate(inflater, container, false).also { createdBinding ->
            binding = createdBinding
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MainPresenter(this)


        //mainAdapter = MainRecyclerAdapter()

        binding?.recyclerView?.run {
            layoutManager = LinearLayoutManager(context)
            adapter = MainRecyclerAdapter().also { createdAdapter ->
                mainAdapter = createdAdapter
            }
        }

        presenter.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun updateData(dataList: List<DummyContents>) {
       // mainAdapter.setItems(dataList)
    }

    override fun showProgressBar() {

    }

    override fun hideProgressBar() {

    }
}