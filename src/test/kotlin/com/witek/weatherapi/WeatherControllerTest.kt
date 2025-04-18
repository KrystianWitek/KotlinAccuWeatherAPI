package com.witek.weatherapi

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.witek.weatherapi.dto.CurrentWeatherResponse
import com.witek.weatherapi.dto.ErrorResponse
import com.witek.weatherapi.dto.Temperature
import com.witek.weatherapi.dto.TemperatureDetails
import feign.FeignException
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.json.JsonCompareMode
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.math.BigDecimal

@WebMvcTest(controllers = [WeatherController::class])
@ActiveProfiles("test")
internal class WeatherControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var accuWeatherService: AccuWeatherService

    @Test
    fun `should read product details`() {
        val response = CurrentWeatherResponse(
            localObservationDate = "localObservationDate",
            weatherText = "weatherText",
            isDayTime = false,
            temperature = Temperature(
                metric = TemperatureDetails(
                    value = BigDecimal("123"),
                    unit = "unit",
                    unitType = 3839
                )
            )
        )
        Mockito.`when`(accuWeatherService.getCurrentWeather(CITY_NAME))
            .thenReturn(response)

        mockMvc.get("/weather/$CITY_NAME")
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(jacksonObjectMapper().writeValueAsString(response), JsonCompareMode.STRICT)
                }
            }

        verify(accuWeatherService).getCurrentWeather(CITY_NAME)
        verifyNoMoreInteractions(accuWeatherService)
    }

    @Test
    fun `should return service unavailable`() {
        Mockito.`when`(accuWeatherService.getCurrentWeather(CITY_NAME))
            .thenThrow(FeignException.FeignServerException::class.java)

        mockMvc.get("/weather/$CITY_NAME")
            .andExpect {
                status { isServiceUnavailable() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    val response = ErrorResponse("Accuweather not available. Please try again later")
                    json(jacksonObjectMapper().writeValueAsString(response), JsonCompareMode.STRICT)
                }
            }

        verify(accuWeatherService).getCurrentWeather(CITY_NAME)
        verifyNoMoreInteractions(accuWeatherService)
    }

    private companion object {
        const val CITY_NAME: String = "GwiezdnaFlota"
    }
}