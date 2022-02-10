package com.mentoring.sample.ui

import android.widget.Toast
import androidx.lifecycle.*
import com.mentoring.sample.MentoringApplication
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
    val uiData : LiveData<MainUiEvent> get() = _uiData


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
                            listData.data.onClick =
                                { id ->
                                    Toast.makeText(
                                        MentoringApplication.context,
                                        "Clicked id : $id",
                                        Toast.LENGTH_SHORT
                                    ).show()
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