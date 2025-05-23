package com.witek.weatherapi

import com.witek.weatherapi.dto.CurrentWeatherResponse
import com.witek.weatherapi.dto.ErrorResponse
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController(
    private val service: AccuWeatherService
) {

    @GetMapping("/weather/{cityName}")
    @ResponseStatus(HttpStatus.OK)
    fun read(@PathVariable cityName: String): CurrentWeatherResponse {
        return service.getCurrentWeather(cityName)
    }

    @ExceptionHandler(FeignException::class)
    private fun handleFeignException(ex: FeignException): ResponseEntity<ErrorResponse> {
        logger.error(ex.message)
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ErrorResponse("Accuweather not available. Please try again later"))
    }

    private companion object {
        val logger: Logger = LoggerFactory.getLogger(WeatherController::class.java)
    }
}