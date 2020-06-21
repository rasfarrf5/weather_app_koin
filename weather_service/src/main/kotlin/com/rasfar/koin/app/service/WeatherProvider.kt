package com.rasfar.koin.app.service

import com.rasfar.koin.app.network.NetworkConfiguration
import io.reactivex.Observable

class WeatherProvider constructor(
    private val weatherService: WeatherService,
    private val weatherDataMapper: WeatherDataMapper,
    private val configuration: NetworkConfiguration
) {

    fun getWeatherInfo(country: String): Observable<WeatherResult> {
        return weatherService.getWeather(country, configuration.getApiKey())
            .map { response ->
                val data = weatherDataMapper.mapFrom(response)
                WeatherResult.Success(data) as WeatherResult
            }
            .onErrorReturn { WeatherResult.Failed }
    }
}