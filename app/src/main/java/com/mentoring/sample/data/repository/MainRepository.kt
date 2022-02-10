package com.mentoring.sample.data.repository

import com.mentoring.sample.data.datasource.MainDataSource
import com.mentoring.sample.util.dummy.DummyContents
import com.shiny.shopping.data.models.ListUIData
import io.reactivex.Single

/**
 *  Local, Remote 에 따라 확장 될 수 있도록,
 *
 */
class MainRepository(private val dataSource: MainDataSource) : IMainRepository {

    override fun getItems(): Single<List<ListUIData>> {
        return dataSource.getItems()
    }

    override suspend fun getSuspendedItems(): List<DummyContents> {
        return dataSource.getSuspendedItems()
    }

}