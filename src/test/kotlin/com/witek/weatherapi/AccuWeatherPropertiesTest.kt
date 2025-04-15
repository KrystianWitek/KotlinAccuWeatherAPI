package com.witek.weatherapi

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.runner.ApplicationContextRunner

class AccuWeatherPropertiesTest {

    private val contextRunner: ApplicationContextRunner by lazy {
        ApplicationContextRunner()
            .withUserConfiguration(AccuWeatherTestConfig::class.java)
            .withInitializer(ConfigDataApplicationContextInitializer())
            .withSystemProperties("ACCU_WEATHER_API_KEY=testKey")
    }

    @Test
    fun `should load properties from application yaml`() {
        contextRunner.run { context ->
            context.getBean(AccuWeatherProperties::class.java).let {
                assertEquals("testKey", it.apikey)
                assertEquals("http://dataservice.accuweather.com", it.url)
            }
        }
    }

    @EnableConfigurationProperties(AccuWeatherProperties::class)
    private class AccuWeatherTestConfig
}
