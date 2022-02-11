package com.mentoring.sample.ui

import androidx.lifecycle.*
import com.mentoring.sample.data.models.ViewType
import com.mentoring.sample.data.repository.IMainRepository
import com.mentoring.sample.ui.base.AbstractViewModel
import com.shiny.shopping.data.models.ListUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: IMainRepository) : AbstractViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var _uiData = MutableLiveData<MainUiEvent>()
    internal val uiData : LiveData<MainUiEvent> get() = _uiData

    var onItemClicked: OnClick? = null


    override fun loadApi() {
        _progressBar.value = true

        val disposable = repository.getItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ dataList ->
                dataList.forEach { listData ->
                    //TODO Multi columns per one row
                    when(listData.viewtype) {
                        ViewType.PRODUCT -> {
                            onItemClicked?.also { onClick ->
                                listData.data.onClick = onClick
                            }
                        }
                    }
                }
                _uiData.value = MainUiEvent.Success(dataList)
            }
        compositeDisposable.add(disposable)

        _progressBar.value = false
    }





    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


    sealed class MainUiEvent {
        data class ShowProgress(val show: Boolean) : MainUiEvent()
        data class Success(val data: List<ListUIData>) : MainUiEvent()
        data class Error(val message: String) : MainUiEvent()
    }
}