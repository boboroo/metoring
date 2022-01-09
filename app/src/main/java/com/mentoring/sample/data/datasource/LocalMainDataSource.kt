package com.mentoring.sample.data.datasource

import com.mentoring.sample.util.dummy.DummyContentBuilder
import com.mentoring.sample.util.dummy.DummyContents
import io.reactivex.Observable
import io.reactivex.Single

class LocalMainDataSource : MainDataSource {
    override fun getItems(): Single<List<DummyContents>> {
        return Single.just(
            DummyContentBuilder().makeDummyContents(30)
        )
    }

    override suspend fun getItems2(): List<DummyContents> {
        return DummyContentBuilder().makeDummyContents(30)
    }
}