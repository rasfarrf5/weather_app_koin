package com.rasfar.koin.app.service

sealed class WeatherResult {

    data class Success(val weatherData: WeatherData) : WeatherResult()

    object Failed : WeatherResult()
}
