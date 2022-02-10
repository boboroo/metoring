package com.mentoring.sample.data.datasource

import com.mentoring.sample.data.api.MainNetworkApi
import com.mentoring.sample.util.dummy.DummyContents
import com.shiny.shopping.data.models.ListUIData
import io.reactivex.Single

class RemoteMainDataSource(private val mainNetworkApi: MainNetworkApi) : MainDataSource {

    override fun getItems(): Single<List<ListUIData>> {
        return mainNetworkApi.getMainData()
    }

    override suspend fun getSuspendedItems(): List<DummyContents> {
        TODO("Not yet implemented")
    }
}