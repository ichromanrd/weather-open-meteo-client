package com.ichromanrd.weather.cache.model

import com.ichromanrd.weather.dto.City
import org.springframework.data.redis.core.RedisHash

@RedisHash("cities")
class CityCache(
    val id: String,
    val name: String,
    val region: String,
    val country: String,
) {

    companion object {
        fun getId(latitude: String, longitude: String) = "city:${latitude}_${longitude}"

        fun fromDto(latitude: String, longitude: String, value: City): CityCache = CityCache(
            getId(latitude, longitude),
            value.name,
            value.region,
            value.country
        )

        fun fromDto(id: String, value: City): CityCache = CityCache(
            id,
            value.name,
            value.region,
            value.country
        )
    }

    fun toDto() = City(
        name,
        region,
        country,
    )
}