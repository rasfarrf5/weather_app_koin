package com.rasfar.koin.app.service

import com.rasfar.koin.app.network.NetworkConfiguration
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import retrofit2.Retrofit

class WeatherApiComponent : KoinComponent {

    init {
        val retrofit: Retrofit by inject()
        val configuration: NetworkConfiguration by inject()
    }

    val weatherProvider: WeatherProvider = get()
}