package com.ichromanrd.weather.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.ichromanrd.weather.common.constants.CurrentVariables

data class OpenMeteoErrorResponse(
    val error: Boolean = true,
    val reason: String = "",
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class OpenMeteoWeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    @JsonProperty("generationtime_ms")
    val generationTimeInMillis: Double,
    val utcOffsetSeconds: Int,
    val timezone: String,
    @JsonProperty("timezone_abbreviation")
    val timezoneAbbreviation: String,
    val current: Current? = null,
    @JsonProperty("current_units")
    val currentUnits: CurrentUnits? = null,
    val hourly: Hourly? = null,
    @JsonProperty("hourly_units")
    val hourlyUnits: HourlyUnits? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Current(
    val time: String? = null,
    val interval: Double? = null,
    @JsonProperty(CurrentVariables.temp2m)
    val temp2m: Double? = null,
    @JsonProperty(CurrentVariables.relativeHumidity2m)
    val relativeHumidity2m: Double? = null,
    @JsonProperty(CurrentVariables.apparentTemp)
    val apparentTemp: Double? = null,
    @JsonProperty(CurrentVariables.isDay)
    val isDay: Int? = null,
    val precipitation: String? = null,
    val rain: Double? = null,
    val showers: Double? = null,
    val snowfall: Double? = null,
    @JsonProperty(CurrentVariables.weatherCode)
    val weatherCode: Int? = null,
    @JsonProperty(CurrentVariables.cloudCover)
    val cloudCover: Double? = null,
    @JsonProperty(CurrentVariables.pressureMs1)
    val pressureMs1: Double? = null,
    @JsonProperty(CurrentVariables.surfacePressure)
    val surfacePressure: Double? = null,
    @JsonProperty(CurrentVariables.windSpeed10m)
    val windSpeed10m: Double? = null,
    @JsonProperty(CurrentVariables.windDirection10m)
    val windDirection10m: Double? = null,
    @JsonProperty(CurrentVariables.windGusts10m)
    val windGusts10m: Double? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CurrentUnits(
    val time: String,
    val interval: String,
    @JsonProperty(CurrentVariables.temp2m)
    val temp2m: String? = null,
    @JsonProperty(CurrentVariables.relativeHumidity2m)
    val relativeHumidity2m: String? = null,
    @JsonProperty(CurrentVariables.apparentTemp)
    val apparentTemp: String? = null,
    @JsonProperty(CurrentVariables.isDay)
    val isDay: String? = null,
    val precipitation: String? = null,
    val rain: String? = null,
    val showers: String? = null,
    val snowfall: String? = null,
    @JsonProperty(CurrentVariables.weatherCode)
    val weatherCode: String? = null,
    @JsonProperty(CurrentVariables.cloudCover)
    val cloudCover: String? = null,
    @JsonProperty(CurrentVariables.pressureMs1)
    val pressureMs1: String? = null,
    @JsonProperty(CurrentVariables.surfacePressure)
    val surfacePressure: String? = null,
    @JsonProperty(CurrentVariables.windSpeed10m)
    val windSpeed10m: String? = null,
    @JsonProperty(CurrentVariables.windDirection10m)
    val windDirection10m: String? = null,
    @JsonProperty(CurrentVariables.windGusts10m)
    val windGusts10m: String? = null,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Hourly(
    val time: List<String>,
    @JsonProperty("temperature_2m")
    val temp2m: List<Double> = emptyList(),
)

data class HourlyUnits(
    @JsonProperty("temperature_2m")
    val temp2m: String,
)