package com.mentoring.sample.ui.mvp

import android.os.Handler
import android.os.Looper
import com.mentoring.sample.util.dummy.DummyContentBuilder
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(
    private var view: MainContract.View?= null
) : MainContract.Presenter {

    override fun loadData() {
        view?.showProgressBar()

        //통신
        Handler(Looper.getMainLooper()).postDelayed( {
            //1초뒤
            view?.updateData(DummyContentBuilder().makeDummyContents(50))

        }, 1000)
        view?.hideProgressBar()
    }

    override fun onDestroy() {
        view = null
    }
}