package com.mentoring.sample.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = RecentlyViewedProduct.TABLE_NAME)
data class RecentlyViewedProduct(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    val image: String,
    val price: Int,
    val name: String,
    val detail: String,

    @ColumnInfo(name = COLUMN_SYSTEM_MILLIS)
    val systemMillis: Long) {


    companion object {
        @Ignore const val TABLE_NAME = "recent_viewed_product"
        @Ignore const val COLUMN_ID = "id"
        @Ignore const val COLUMN_SYSTEM_MILLIS = "system_millis"
    }

}