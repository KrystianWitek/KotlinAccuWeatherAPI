package com.witek.weatherapi

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import kotlin.test.assertFailsWith

class AccuWeatherServiceTest {
    private val client: AccuWeatherClient = mock()
    private val properties: AccuWeatherProperties = mock()
    private lateinit var service: AccuWeatherService

    @BeforeEach
    fun setUp() {
        reset(client, properties)
        `when`(properties.apikey).thenReturn("apikey")
        service = AccuWeatherService(client, properties)
    }

    @Test
    fun `should throw exception, when locationKey is missing`() {
        `when`(client.getLocationKey(anyString(), anyString()))
            .thenReturn(emptyList())

        assertFailsWith<IllegalStateException> {
            service.getCurrentWeather("cityName")
        }.let {
            assertEquals(it.message, "Missing location key!")
        }

        verify(client).getLocationKey(apiKey = "apikey", cityName = "cityName")
        verifyNoMoreInteractions(client)
    }
}