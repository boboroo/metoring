package com.mentoring.sample.di.koin

import com.mentoring.sample.data.datasource.MainDataSource
import com.mentoring.sample.data.datasource.RemoteMainDataSource
import com.mentoring.sample.data.repository.IMainRepository
import com.mentoring.sample.data.repository.MainRepository
import com.mentoring.sample.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module(override = true) {

    single<MainDataSource>{
        RemoteMainDataSource(get())
    }
    single<IMainRepository>{
        MainRepository(get())
    }
    viewModel {
        (testStr: String) ->
        MainViewModel(testStr, get())
    }
}