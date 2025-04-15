package com.witek.weatherapi

import com.witek.weatherapi.dto.CurrentWeatherResponse

class AccuWeatherService(
    private val client: AccuWeatherClient,
    private val properties: AccuWeatherProperties
) {

    fun getCurrentWeather(cityName: String): CurrentWeatherResponse {
        val locationKey: String = obtainLocationKey(cityName)
        val currentWeather: CurrentWeatherResponse = obtainCurrentWeather(locationKey)
        return currentWeather
    }

    private fun obtainLocationKey(cityName: String): String =
        client
            .getLocationKey(apiKey = properties.apikey, cityName = cityName)
            .firstOrNull()?.key
            ?: error("Missing location key!")

    private fun obtainCurrentWeather(locationKey: String): CurrentWeatherResponse =
        client.getCurrentConditions(locationKey = locationKey, apiKey = properties.apikey)
            .firstOrNull()
            ?: error("Unable to get current weather for $locationKey")
}