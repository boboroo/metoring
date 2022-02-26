package com.mentoring.sample.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mentoring.sample.R
import com.mentoring.sample.databinding.FragmentMainBinding
import com.mentoring.sample.ui.adapter.MainRecyclerAdapter
import com.mentoring.sample.ui.base.AbstractPersistentFragment
import com.mentoring.sample.ui.base.AbstractViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : AbstractPersistentFragment<FragmentMainBinding, MainViewModel>() {

     //normal
//     override val viewModel by viewModels<MainViewModel> {
//         object: ViewModelProvider.Factory {
//             override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                 return MainViewModel(MainRepository(LocalMainDataSource())) as T
//             }
//         }
//     }
     //koin
//    override val viewModel by viewModel<MainViewModel> {
//        parametersOf("우유")
//    }

    //hilt
    override val viewModel : MainViewModel by activityViewModels()

    @Inject
    lateinit var mainAdapter: MainRecyclerAdapter

    companion object {
        fun newInstance(s: String) = MainFragment().apply {
            arguments = Bundle().apply {
                putString("kth", s)
            }
        }
    }

    override fun getResourceId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(root: View) {
        binding.recyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }
//        binding.btnOpen.setOnClickListener {
//            childFragmentManager.commit {
//                add(R.id.container_above, DetailFragment.newInstance())
//                addToBackStack(null)
//            }
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
    }
    override fun initViewModel() {
        /**
         * eventObserver 는 한번만 받기 때문에 사용해도 될것 처럼보인다.
         * 하지만 loadData()가 두번 호출되기 때문에 API는 두번 탄다.
         * 1. loadData() 호출 1번 하는 방법
         * 2. https://bb-library.tistory.com/271
         */
//        viewModel.uiData2.observe(viewLifecycleOwner, EventObserver { uiEvent ->
//            Logger.d("uiEvent : $uiEvent")
//            when(uiEvent) {
//                is MainViewModel.MainUiEvent.ShowProgress -> {
//                }
//                is MainViewModel.MainUiEvent.Success -> {
//                    mainAdapter.setItems(uiEvent.data)
//                }
//                is MainViewModel.MainUiEvent.Error -> {
//                    Toast.makeText(context, uiEvent.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

        //버그 수정됨.
        viewModel.uiData.observe(this, Observer { uiEvent ->
            Logger.d("uiEvent : $uiEvent")
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("onCreate :" + arguments?.getString("key"))
    }


    override fun onDetach() {
        super.onDetach()
        Logger.d("onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.d("onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.d("onDestroyView")
    }

}