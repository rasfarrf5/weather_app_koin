package com.rasfar.koin.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {

//    override fun attachBaseContext(context: Context) {
//        MultiDex.install(this)
//        super.attachBaseContext(context)
//    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            androidLogger()
        }

        KoinInit.init()
    }
}