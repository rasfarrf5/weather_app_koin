package com.rasfar.koin.app.service

data class WeatherResponse(
    val name: String,
    val main: Main
) {
    data class Main(val temp: Double)
}