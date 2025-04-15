package com.witek.weatherapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients(basePackages = ["com.witek.weatherapi"])
@EnableConfigurationProperties(AccuWeatherProperties::class)
class WeatherApiApplication

fun main(args: Array<String>) {
    runApplication<WeatherApiApplication>(*args)
}
