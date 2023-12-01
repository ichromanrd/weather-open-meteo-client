package com.ichromanrd.weather.rest

import com.ichromanrd.weather.common.constants.CurrentVariables.defaultCurrentVariables
import com.ichromanrd.weather.common.errors.ApiException
import com.ichromanrd.weather.config.OpenMeteoProperties
import com.ichromanrd.weather.model.OpenMeteoErrorResponse
import com.ichromanrd.weather.model.OpenMeteoWeatherResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@Component
class OpenMeteoApiClient(
    private val webClient: WebClient,
    private val openMeteoProperties: OpenMeteoProperties,
) {

    fun currentWeather(
        latitude: String?,
        longitude: String?,
        timezone: String?
    ): Mono<OpenMeteoWeatherResponse> {
        val uri = OpenMeteoV1ApiSpec.Builder()
            .uri(openMeteoProperties.url[OpenMeteoV1ApiSpec.v1]!!)
            .latitude(latitude)
            .longitude(longitude)
            .timezone(timezone)
            .currentVariables(defaultCurrentVariables)
            .build()
            .currentUri()
        return webClient.get()
            .uri(uri)
            .retrieve()
            .onStatus(
                { it.isSameCodeAs(HttpStatus.BAD_REQUEST) },
                { clientResponse ->
                    clientResponse.bodyToMono(OpenMeteoErrorResponse::class.java)
                        .map {
                            ApiException(
                                HttpStatus.valueOf(clientResponse.statusCode().value()),
                                it.reason
                            )
                        }
                }
            )
            .bodyToMono(OpenMeteoWeatherResponse::class.java)
    }

}

class OpenMeteoV1ApiSpec private constructor(
    private val url: String,
    private val latitude: String?,
    private val longitude: String?,
    private val timezone: String?,
    private val currentVariables: List<String>?,
) {
    companion object {
        const val v1 = "v1"
    }

    private fun forecastUri(): UriComponentsBuilder =
        UriComponentsBuilder.fromUriString("$url/forecast")
            .apply {
                latitude?.let { this.query("latitude=$it") }
                longitude?.let { this.query("longitude=$it") }
                timezone?.let { this.query("timezone=$timezone") }
            }

    fun currentUri(): String =
        forecastUri().apply {
            currentVariables?.let { if (it.isNotEmpty()) this.query("current=${it.joinToString(",")}") }
        }
            .toUriString()


    data class Builder(
        var uri: String? = null,
        var latitude: String? = null,
        var longitude: String? = null,
        var timezone: String? = null,
        var currentVariables: List<String>? = null,
    ) {
        fun uri(uri: String) = apply { this.uri = uri }
        fun latitude(latitude: String?) = apply { this.latitude = latitude }
        fun longitude(longitude: String?) = apply { this.longitude = longitude }
        fun timezone(timezone: String?) = apply { this.timezone = timezone }
        fun currentVariables(vars: List<String>?) = apply { this.currentVariables = vars }

        fun build() = OpenMeteoV1ApiSpec(uri!!, latitude, longitude, timezone, currentVariables)
    }

}