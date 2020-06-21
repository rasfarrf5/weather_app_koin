package com.rasfar.koin.app.service

import com.rasfar.koin.app.network.NetworkConfiguration
import org.koin.dsl.module
import retrofit2.Retrofit

val weatherApiModule = module {
    factory { providesWeatherProvider(get(), get()) }

    single { provideWeatherService(get()) }
}

fun providesWeatherProvider(
    service: WeatherService,
    configuration: NetworkConfiguration
): WeatherProvider {
    return WeatherProvider(service, WeatherDataMapper(), configuration)
}

fun provideWeatherService(retrofit: Retrofit): WeatherService =
    retrofit.create(WeatherService::class.java)
