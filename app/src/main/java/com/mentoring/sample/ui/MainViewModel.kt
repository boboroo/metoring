package com.mentoring.sample.ui

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.*
import com.mentoring.sample.MentoringApplication
import com.mentoring.sample.R
import com.mentoring.sample.data.repository.IMainRepository
import com.mentoring.sample.ui.base.AbstractViewModel
import com.mentoring.sample.util.dummy.DummyContents
import com.mentoring.sample.util.ex.Event
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val textStr: String,
    private val repository: IMainRepository) : AbstractViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var _uiData = MutableLiveData<MainUiEvent>()
    val uiData : LiveData<MainUiEvent> get() = _uiData

    private var _uiData2 = MutableLiveData<Event<MainUiEvent>>()
    val uiData2 : LiveData<Event<MainUiEvent>> get() = _uiData2

    fun loadData() {
        //_uiData.value = MainUiEvent.ShowProgress(true)
        _progressBar.value = true
        Logger.d("out")

//        viewModelScope.launch(Dispatchers.IO) {
//            Logger.d("in")
//            val result = repository.getItems2()
//            delay(3000)
//            Logger.d("in2")
//            delay(3000)
//            Logger.d("in3")
//            delay(3000)
//            Logger.d("in4")
//            delay(3000)
//            _uiData.postValue( MainUiEvent.Success(result))
//        }

        val disposable = repository.getItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dataList ->
                //Handler(Looper.getMainLooper()).postDelayed( {
                //UI를 그리는 로직이 상당히 많다. (5초)
                    _uiData.value = MainUiEvent.Success(dataList)
                //}, 5000)
            }
        compositeDisposable.add(disposable)

        _progressBar.value = false
        //_uiData.value = MainUiEvent.ShowProgress(false)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    sealed class MainUiEvent {
        data class ShowProgress(val show: Boolean) : MainUiEvent()
        data class Success(val data: List<DummyContents>) : MainUiEvent()
        data class Error(val message: String) : MainUiEvent()
    }
}