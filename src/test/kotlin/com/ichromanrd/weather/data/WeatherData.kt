package com.ichromanrd.weather.data

import com.ichromanrd.weather.cache.model.WeatherCache
import com.ichromanrd.weather.model.Current
import com.ichromanrd.weather.model.CurrentUnits
import com.ichromanrd.weather.model.OpenMeteoWeatherResponse

object WeatherData {

    val openMeteoResponse = OpenMeteoWeatherResponse(
        -6.288670,
        106.719690,
        38.0,
        0.06103515625,
        25200,
        "Asia/Jakarta",
        "WIB",
        currentUnits = CurrentUnits(
            "iso8601",
            "seconds",
            isDay = "",
            apparentTemp = "°C",
            weatherCode = "wmo code"
        ),
        current = Current(
            "2023-12-05T20:30",
            900.0,
            isDay = 1,
            apparentTemp = 33.9,
            weatherCode = 71,
        )
    )

    val weatherCacheData = WeatherCache(
        "some-id",
        "2023-12-05T09:45 WIB",
        "36.6 °C",
        "Overcast",
        "Ciputat, South Tangerang, Indonesia"
    )

    val weatherData = weatherCacheData.toDto()

}