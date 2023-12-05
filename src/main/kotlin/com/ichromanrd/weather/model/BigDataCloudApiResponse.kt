package com.ichromanrd.weather.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

data class BigDataCloudApiErrorResponse(
    val status: Int,
    val description: String,
)

@JsonIgnoreProperties(ignoreUnknown = false)
data class ReverseGeocodingResponse(
    val latitude: Double,
    val longitude: Double,
    val lookupSource: String,
    val localityLanguageRequested: String,
    val continent: String,
    val continentCode: String,
    val countryName: String,
    val countryCode: String,
    val principalSubdivision: String,
    val principalSubdivisionCode: String,
    val city: String,
    val locality: String,
    val postcode: String,
    val plusCode: String,
)