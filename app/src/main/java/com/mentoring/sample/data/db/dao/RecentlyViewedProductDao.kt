package com.mentoring.sample.data.db.dao

import androidx.room.*
import com.mentoring.sample.data.db.entities.RecentlyViewedProduct

@Dao
interface RecentlyViewedProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: RecentlyViewedProduct): Long

    @Query("SELECT * FROM " + RecentlyViewedProduct.TABLE_NAME + " ORDER BY " + RecentlyViewedProduct.COLUMN_SYSTEM_MILLIS + " DESC")
    fun loadAllData(): List<RecentlyViewedProduct>

    @Query("SELECT * FROM " + RecentlyViewedProduct.TABLE_NAME + " WHERE " + RecentlyViewedProduct.COLUMN_ID + " = :productId")
    fun findById(productId: String): RecentlyViewedProduct

}