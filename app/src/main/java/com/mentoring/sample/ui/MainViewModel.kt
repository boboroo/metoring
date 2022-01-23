package com.mentoring.sample.ui

import androidx.lifecycle.*
import com.mentoring.sample.data.repository.IMainRepository
import com.mentoring.sample.ui.adapter.MainRecyclerAdapter
import com.mentoring.sample.ui.base.AbstractViewModel
import com.mentoring.sample.ui.model.UIModel
import com.mentoring.sample.util.dummy.DummyImageContents
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IMainRepository) : AbstractViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var _uiData = MutableLiveData<MainUiEvent>()
    val uiData : LiveData<MainUiEvent> get() = _uiData

    override fun loadApi() {
        //Logger.d("savedStateHandle : " +savedStateHandle.get("key"))
        //_uiData.value = MainUiEvent.ShowProgress(true)
        _progressBar.value = true

        val disposable = repository.getItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dataList ->
                Logger.d("MainViewModel API Response")
                val uiModelList = arrayListOf<UIModel>()
                dataList.forEachIndexed { index, dummyContents ->
                    when(index % 2 == 0) {
                        true -> {
                            uiModelList.add(UIModel.DummyUIModel(dummyContents, MainRecyclerAdapter.PRODUCT_TYPE))
                        }
                        false -> {
                            uiModelList.add(UIModel.ImageUIModel(
                                DummyImageContents(index.toString(),
                                    "https://images.unsplash.com/photo-1490885578174-acda8905c2c6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=300&q=80"),
                                MainRecyclerAdapter.IMAGE_TYPE))
                        }
                    }
                }
                _uiData.value = MainUiEvent.Success(uiModelList)
            }
        compositeDisposable.add(disposable)

        //_uiData.value = MainUiEvent.ShowProgress(false)
        _progressBar.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    sealed class MainUiEvent {
        data class ShowProgress(val show: Boolean) : MainUiEvent()
        data class Success(val data: List<UIModel>) : MainUiEvent()
        data class Error(val message: String) : MainUiEvent()
    }
}