package com.witek.weatherapi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AccuWeatherConfig(
    private val client: AccuWeatherClient,
    private val properties: AccuWeatherProperties
) {

    @Bean
    open fun accuWeatherService(): AccuWeatherService =
        AccuWeatherService(client, properties)
}