package com.witek.weatherapi

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "accuweather")
data class AccuWeatherProperties @ConstructorBinding constructor(
    @field:NotBlank val apikey: String,
    @field:NotBlank val url: String
)