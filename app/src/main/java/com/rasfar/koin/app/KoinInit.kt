package com.rasfar.koin.app

import com.rasfar.koin.app.main.mainModule
import com.rasfar.koin.app.network.networkModule
import com.rasfar.koin.app.service.weatherApiModule
import org.koin.core.context.loadKoinModules

object KoinInit {
    fun init() = loadKoinModules(
        listOf(
            appModule,
            mainModule,
            weatherApiModule,
            networkModule
        )
    )
}