package com.mentoring.sample.di.hilt

import com.mentoring.sample.data.datasource.LocalMainDataSource
import com.mentoring.sample.data.datasource.MainDataSource
import com.mentoring.sample.data.repository.IMainRepository
import com.mentoring.sample.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideMainDataSource() : MainDataSource {
        return LocalMainDataSource()
    }

    @Singleton
    @Provides
    fun provideSearchRepository(dataSource: MainDataSource) : IMainRepository {
        return MainRepository(dataSource)
    }
}