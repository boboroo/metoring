package com.mentoring.sample.data.repository

import com.mentoring.sample.util.dummy.DummyContents
import com.shiny.shopping.data.models.ListUIData
import io.reactivex.Single

interface IMainRepository {
    fun getItems(): Single<List<ListUIData>>
    suspend fun getSuspendedItems(): List<DummyContents>
}