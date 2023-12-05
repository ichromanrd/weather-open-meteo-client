package com.ichromanrd.weather.service

import com.ichromanrd.weather.cache.WeatherCacheService
import com.ichromanrd.weather.data.WeatherData.weatherData
import com.ichromanrd.weather.dto.request.WeatherApiRequestParam
import com.ichromanrd.weather.rest.OpenMeteoApiClient
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class WeatherServiceTest {

    private val openMeteoApiClientMock = mockk<OpenMeteoApiClient>()
    private val cityServiceMock = mockk<CityService>()
    private val weatherCacheServiceMock = mockk<WeatherCacheService>()
    private val weatherService = WeatherService(
        openMeteoApiClientMock,
        cityServiceMock,
        weatherCacheServiceMock,
    )

    @Test
    fun `should throw error if latitude or longitude value are invalid`() {
        every { weatherCacheServiceMock.get(any(), any()) } returns Mono.just(weatherData)
        val result = weatherService.getCurrentWeather(
            WeatherApiRequestParam(
                "invalid", "invalid", "timezone"
            )
        )
        StepVerifier.create(result)
            .consumeErrorWith {
                assertEquals("Invalid latitude or longitude values", it.message)
            }
            .verify()
    }

    @Test
    fun `should fetch from cache and return response if found`() {
        every { weatherCacheServiceMock.get(any(), any()) } returns Mono.just(weatherData)
        val result = weatherService.getCurrentWeather(
            WeatherApiRequestParam(
            "105.1234", "6.03023", "timezone"
            )
        )

        StepVerifier.create(result)
            .consumeNextWith { obj ->
                assertEquals(obj.time, weatherData.time)
                assertEquals(obj.location, weatherData.location)
                assertEquals(obj.temperature, weatherData.temperature)
                assertEquals(obj.weatherCondition, weatherData.weatherCondition)
            }
            .verifyComplete()

        verify(exactly = 1) { weatherCacheServiceMock.get(any(), any()) }
        verify(exactly = 0) { openMeteoApiClientMock.currentWeather(any(), any(), any()) }
        verify(exactly = 0) { cityServiceMock.getCity(any(), any()) }
    }
}