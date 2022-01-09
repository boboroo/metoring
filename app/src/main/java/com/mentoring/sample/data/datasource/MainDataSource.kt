package com.mentoring.sample.data.datasource

import com.mentoring.sample.util.dummy.DummyContents
import io.reactivex.Single

interface MainDataSource {
    fun getItems(): Single<List<DummyContents>>
    suspend fun getItems2(): List<DummyContents>
}