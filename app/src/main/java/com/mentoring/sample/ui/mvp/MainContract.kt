package com.mentoring.sample.ui.mvp

import com.mentoring.sample.util.dummy.DummyContents

interface MainContract {

    interface Presenter {
        fun loadData()
        fun onDestroy()
    }

    interface View {
        fun updateData(dataList: List<DummyContents>)
        fun showProgressBar()
        fun hideProgressBar()
    }
}