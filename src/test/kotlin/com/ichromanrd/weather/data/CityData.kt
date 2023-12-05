package com.ichromanrd.weather.data

import com.ichromanrd.weather.cache.model.CityCache

object CityData {
    val cityCache = CityCache(
        "some-id",
        "Ciputat",
        "South Tangerang",
        "Indonesia"
    )

    val city = cityCache.toDto()
}