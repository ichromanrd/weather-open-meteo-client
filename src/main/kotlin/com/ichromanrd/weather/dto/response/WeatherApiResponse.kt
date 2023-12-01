package com.ichromanrd.weather.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class WeatherApiResponse(
    val time: String,
    val temperature: String,
    val weatherCondition: String? = null,
    val location: String? = null,
)