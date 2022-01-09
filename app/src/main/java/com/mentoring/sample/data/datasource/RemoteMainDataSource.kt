package com.mentoring.sample.data.datasource

import com.mentoring.sample.data.api.MainNetworkApi
import com.mentoring.sample.util.dummy.DummyContents
import io.reactivex.Single

class RemoteMainDataSource(private val mainNetworkApi: MainNetworkApi) : MainDataSource {

    override fun getItems(): Single<List<DummyContents>> {
        return mainNetworkApi.getMainData()
    }

    override suspend fun getItems2(): List<DummyContents> {
        TODO("Not yet implemented")
    }
}