package com.mentoring.sample.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class AbstractViewModel : ViewModel() {

    //프로그래스바, 토스트, 팝업
    protected var _progressBar = MutableLiveData<Boolean>()
    val progressBar : LiveData<Boolean> get() = _progressBar

    // AbstractViewModel -> AbstractLoginViewModel(로그인 관련된 공통 기능들이 포함)
    // AbstractBindingFragment -> AbstractLoginBindingFragment (로그인 관련 공통 기능 )
    private var firstLoadData = true
    private val pageCnt = 1

    abstract fun loadApi()


    fun loadData(force: Boolean=false) {
        if(firstLoadData || force) {
            loadApi()
            firstLoadData = false
        }
    }


    sealed class UiEvent<T> {
        data class ShowProgress<Nothing>(val show: Boolean) : UiEvent<Nothing>()
        data class Success<T>(val data: List<T>) : UiEvent<T>()
        data class Error<Nothing>(val message: String) : UiEvent<Nothing>()
    }
}