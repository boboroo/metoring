package com.mentoring.sample.di.koin

import org.koin.dsl.module

val koinModule = module(override = true) {
/*
    single<MainDataSource>{
        RemoteMainDataSource(get())
    }
    single<IMainRepository>{
        MainRepository(get())
    }
    viewModel {
        (keyword: String) ->
        MainViewModel(keyword, get())
    }*/
}