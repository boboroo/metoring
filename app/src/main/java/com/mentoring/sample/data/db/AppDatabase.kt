package com.mentoring.sample.data.db

import androidx.room.Database
import com.mentoring.sample.data.db.entities.RecentlyViewedProduct
import androidx.room.RoomDatabase
import com.mentoring.sample.data.db.dao.RecentlyViewedProductDao
import androidx.room.Room
import com.mentoring.sample.MentoringApplication

@Database(
    version = 1,
    entities = [RecentlyViewedProduct::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            MentoringApplication.context,
                            AppDatabase::class.java,
                            "app_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }


    abstract fun recentClickedProductDao(): RecentlyViewedProductDao

}