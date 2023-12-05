package com.ichromanrd.weather.cache.model

import com.ichromanrd.weather.dto.response.WeatherApiResponse
import org.springframework.data.redis.core.RedisHash

@RedisHash("weather")
data class WeatherCache(
    val id: String,
    val time: String,
    val temperature: String,
    val weatherCondition: String?,
    val location: String?,
) {
    companion object {
        fun getId(
            latitude: String,
            longitude: String,
            timezone: String
        ) = "weather:${latitude}_${longitude}_${timezone}"

        fun fromDto(
            latitude: String,
            longitude: String,
            timezone: String,
            value: WeatherApiResponse
        ): WeatherCache = fromDto(getId(latitude, longitude, timezone), value)

        fun fromDto(
            id: String,
            value: WeatherApiResponse
        ): WeatherCache = WeatherCache(
            id,
            value.time,
            value.temperature,
            value.weatherCondition,
            value.location,
        )
    }

    fun toDto() = WeatherApiResponse(
        time,
        temperature,
        weatherCondition,
        location
    )
}