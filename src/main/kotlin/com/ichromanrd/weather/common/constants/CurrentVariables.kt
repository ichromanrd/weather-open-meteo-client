package com.ichromanrd.weather.common.constants

object CurrentVariables {
    // temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,
    // weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m
    const val temp2m = "temperature_2m"
    const val relativeHumidity2m = "relative_humidity_2m"
    const val apparentTemp = "apparent_temperature"
    const val isDay = "is_day"
    const val precipitation = "precipitation"
    const val rain = "rain"
    const val showers = "showers"
    const val snowfall = "snowfall"
    const val weatherCode = "weather_code"
    const val cloudCover = "cloud_cover"
    const val pressureMs1 = "pressure_msl"
    const val surfacePressure = "surface_pressure"
    const val windSpeed10m = "wind_speed_10m"
    const val windDirection10m = "wind_direction_10m"
    const val windGusts10m = "wind_gusts_10m"

    val defaultCurrentVariables = arrayListOf(apparentTemp, isDay, weatherCode)
}