package com.witek.weatherapi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AccuWeatherConfig(
    private val client: AccuWeatherClient,
    private val properties: AccuWeatherProperties
) {

    @Bean
    fun accuWeatherService(): AccuWeatherService =
        AccuWeatherService(client, properties)
}