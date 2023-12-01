package com.ichromanrd.weather.dto.request

import com.ichromanrd.weather.common.errors.ApiException
import org.springframework.http.HttpStatus

data class WeatherApiRequestParam(
    val latitude: String? = null,
    val longitude: String? = null,
    val timezone: String? = null,
) {

    fun validateLatLong() {
        val insufficientValues = (latitude != null && longitude == null) ||
                (latitude == null && longitude != null)
        if (insufficientValues)
            throw ApiException(
                HttpStatus.BAD_REQUEST,
                "Invalid latitude or longitude values",
            )

        val multipleLats = latitude!!.split(",")
        val multipleLongs = longitude!!.split(",")
        if (multipleLats.size != multipleLongs.size)
            throw ApiException(
                HttpStatus.BAD_REQUEST,
                "Invalid latitude or longitude values",
            )

        val validLats = multipleLats.all { it.toDoubleOrNull() != null }
        val validLongs = multipleLongs.all { it.toDoubleOrNull() != null }

        if (!validLats || !validLongs)
            throw ApiException(
                HttpStatus.BAD_REQUEST,
                "Invalid latitude or longitude values",
            )
    }
}