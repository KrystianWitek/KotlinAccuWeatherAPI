package com.witek.weatherapi

import com.witek.weatherapi.dto.CurrentWeatherResponse
import com.witek.weatherapi.dto.LocationKeyDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "accuWeather",
    url = "\${accuweather.url}",
    configuration = [FeignConfig::class]
)
interface AccuWeatherClient {

    @GetMapping(
        value = ["/locations/v1/cities/search"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getLocationKey(
        @RequestParam("apikey") apiKey: String,
        @RequestParam("q") cityName: String
    ): List<LocationKeyDto>

    @GetMapping(
        value = ["/currentconditions/v1/{locationKey}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getCurrentConditions(
        @PathVariable locationKey: String,
        @RequestParam("apikey") apiKey: String,
    ): List<CurrentWeatherResponse>
}