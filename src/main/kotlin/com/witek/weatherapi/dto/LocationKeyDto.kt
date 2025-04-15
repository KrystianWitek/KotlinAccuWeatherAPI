package com.witek.weatherapi.dto

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
data class LocationKeyDto(

    @com.fasterxml.jackson.annotation.JsonProperty("Key")
    val key: String
)