package com.mentoring.sample.data.datasource

import com.mentoring.sample.R
import com.mentoring.sample.util.dummy.DummyContentBuilder
import com.mentoring.sample.util.dummy.DummyContents
import com.mentoring.sample.util.ex.loadRawResource
import io.reactivex.Observable
import io.reactivex.Single


class LocalMainDataSource : MainDataSource {
    override fun getItems(): Single<List<DummyContents>> {
        var resId = R.raw.ex01
        //TODO api 받는 response 만들기
        //val response = loadRawResource<"">(resId)

        return Single.just(
            DummyContentBuilder().makeDummyContents(30)
        )
    }

    override suspend fun getItems2(): List<DummyContents> {
        return DummyContentBuilder().makeDummyContents(30)
    }
}