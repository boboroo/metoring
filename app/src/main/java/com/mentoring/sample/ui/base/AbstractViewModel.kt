package com.mentoring.sample.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentoring.sample.ui.MainViewModel

abstract class AbstractViewModel : ViewModel() {

    //프로그래스바, 토스트, 팝업
    protected var _progressBar = MutableLiveData<Boolean>()
    val progressBar : LiveData<Boolean> get() = _progressBar

    // AbstractViewModel -> AbstractLoginViewModel(로그인 관련된 공통 기능들이 포함)
    // AbstractBindingFragment -> AbstractLoginBindingFragment (로그인 관련 공통 기능 )
}