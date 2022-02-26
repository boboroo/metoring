package com.mentoring.sample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mentoring.sample.data.db.helpers.RecentProductDbHelper
import com.mentoring.sample.data.models.ViewType
import com.mentoring.sample.data.repository.IMainRepository
import com.mentoring.sample.ui.base.AbstractViewModel
import com.orhanobut.logger.Logger
import com.shiny.shopping.data.models.ListUIData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: IMainRepository) : AbstractViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var _uiData = MutableLiveData<UiEvent<ListUIData>>()
    internal val uiData : LiveData<UiEvent<ListUIData>> get() = _uiData

    var onItemClickListener: OnItemClickListener? = null
        set(value) {
            if (value is OnItemClickListener) {
                field = OnItemClickListener { data ->
                    RecentProductDbHelper.insertProduct(data)
                        .subscribe { cnt ->
                            Logger.d(
                                if (cnt > 0) "the Recently view product data is added"
                                else "The recently view product is not added" )
                        }

                    value.invoke(data)
                }
            }
        }


    override fun loadApi() {
        _progressBar.value = true

        val disposable = repository.getItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ dataList ->
                dataList.forEach { listData ->
                    //TODO Multiple columns in one row
                    when(listData.viewtype) {
                        ViewType.PRODUCT -> {
                            onItemClickListener?.also { onClick ->
                                listData.data.onItemClickListener = onClick
                            }
                        }
                    }
                }
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