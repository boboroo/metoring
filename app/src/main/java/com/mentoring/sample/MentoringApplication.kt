package com.mentoring.sample

import android.app.Application
import com.mentoring.sample.di.koin.koinModule
import dagger.hilt.android.HiltAndroidApp
import leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


@HiltAndroidApp
class MentoringApplication : Application(){

    init {
        context = this
    }

    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            androidContext(context)
//            modules(koinModule)
//        }

        Logger.addLogAdapter(AndroidLogAdapter())
    }

    companion object {
       lateinit var context : MentoringApplication
    }
}