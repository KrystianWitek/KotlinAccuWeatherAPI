package com.witek.weatherapi.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class CurrentWeatherResponse(

    @JsonProperty("LocalObservationDateTime")
    val localObservationDate: String,

    @JsonProperty("WeatherText")
    val weatherText: String,

    @JsonProperty("IsDayTime")
    val isDayTime: Boolean,

    @JsonProperty("Temperature")
    val temperature: Temperature
)

data class Temperature(

    @JsonProperty("Metric")
    val metric: TemperatureDetails
)

data class TemperatureDetails(
    @JsonProperty("Value")
    val value: BigDecimal,

    @JsonProperty("Unit")
    val unit: String,

    @JsonProperty("UnitType")
    val unitType: Int
)