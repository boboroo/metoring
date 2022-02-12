package com.mentoring.sample.data.db.helpers

import com.mentoring.sample.data.db.AppDatabase
import com.mentoring.sample.data.db.dao.RecentlyViewedProductDao
import com.mentoring.sample.data.db.entities.RecentlyViewedProduct
import com.mentoring.sample.data.models.UIData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object RecentProductDbHelper {

    private val mAppDatabase: AppDatabase
    private val mDao: RecentlyViewedProductDao

    init {
        mAppDatabase = AppDatabase.getInstance()!!
        mDao = mAppDatabase.recentClickedProductDao()
    }

    fun insertProduct(uiData: UIData): Observable<Long> {
        return Observable.fromCallable {
            val data = RecentlyViewedProduct(
                uiData.id,
                uiData.image,
                uiData.price,
                uiData.name,
                uiData.image,
                System.currentTimeMillis()
            )
            mDao.insert(data)
        }
            .subscribeOn(Schedulers.io())
    }


    fun loadAllProducts(): Observable<List<RecentlyViewedProduct>> {
        return Observable.fromCallable { mDao.loadAllData() }
            .subscribeOn(Schedulers.io())
    }


    fun findByProductId(productId: String): Observable<RecentlyViewedProduct> {
        return Observable.fromCallable { mDao.findById(productId!!) }
            .subscribeOn(Schedulers.io())
    }

}