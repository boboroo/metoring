package com.mentoring.sample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mentoring.sample.data.db.entities.RecentlyViewedProduct
import com.mentoring.sample.data.db.helpers.RecentProductDbHelper
import com.mentoring.sample.ui.base.AbstractViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class RecentlyProductsViewModel @Inject constructor() : AbstractViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var _uiData = MutableLiveData<UiEvent<RecentlyViewedProduct>>()
    internal val uiData : LiveData<UiEvent<RecentlyViewedProduct>> get() = _uiData


    override fun loadApi() {
        _progressBar.value = true

        val disposable = RecentProductDbHelper.loadAllProducts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ dataList ->
                _uiData.value = UiEvent.Success(dataList)
            }
        compositeDisposable.add(disposable)

        _progressBar.value = false
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}