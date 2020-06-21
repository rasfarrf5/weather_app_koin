package com.rasfar.koin.app.service

class WeatherDataMapper {

    fun mapFrom(response: WeatherResponse): WeatherData {
        return response.convert()
    }

    private fun WeatherResponse.convert(): WeatherData {
        return WeatherData(
            name = name,
            temperature = main.temp
        )
    }
}