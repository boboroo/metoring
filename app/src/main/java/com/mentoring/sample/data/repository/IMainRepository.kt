package com.mentoring.sample.data.repository

import com.mentoring.sample.util.dummy.DummyContents
import io.reactivex.Single

interface IMainRepository {
    fun getItems(): Single<List<DummyContents>>
    suspend fun getItems2(): List<DummyContents>
}