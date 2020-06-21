package com.rasfar.koin.app

import android.content.Context
import com.rasfar.koin.app.network.NetworkConfiguration
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class NetworkConfigurationImpl(private val context: Context) : NetworkConfiguration {

    override fun getApiKey(): String = "REPLAY_YOUR_API_KEY" // https://openweathermap.org/

    override fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioScheduler(): Scheduler = Schedulers.io()

    override fun getHost(): String {
        return if (BuildConfig.DEBUG) "http://localhost:8080" else "http://api.openweathermap.org/data/2.5/"
    }

    override fun getCacheDir(): File = context.cacheDir

    override fun getCacheSize(): Long = 10 * 1024 * 1024

    override fun getTimeoutSeconds(): Long = 5
}