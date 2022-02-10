package com.mentoring.sample.data.datasource

import com.mentoring.sample.R
import com.mentoring.sample.util.dummy.DummyContentBuilder
import com.mentoring.sample.util.dummy.DummyContents
import com.mentoring.sample.util.ex.loadRawResource
import com.shiny.shopping.data.models.ListUIData
import com.shiny.shopping.data.models.ListResponse
import io.reactivex.Single


class LocalMainDataSource : MainDataSource {

    override fun getItems(): Single<List<ListUIData>> {
        val resId = R.raw.ex01
        val response = loadRawResource<ListResponse>(resId)
        return when(response.result) {
            "200" -> {
                Single.just(response.data)
            }
            else -> {
                throw IllegalStateException("Response code isn't 200 at getItems() in LocalMainDataSource.kt")
            }
        }
    }


    override suspend fun getSuspendedItems(): List<DummyContents> {
        return DummyContentBuilder().makeDummyContents(30)
    }

}