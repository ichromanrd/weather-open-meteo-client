package com.ichromanrd.weather.rest

import com.ichromanrd.weather.common.errors.ApiException
import com.ichromanrd.weather.config.BigDataCloudApiProperties
import com.ichromanrd.weather.model.BigDataCloudApiErrorResponse
import com.ichromanrd.weather.model.ReverseGeocodingResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@Component
class BigDataCloudApiClient(
    private val bigDataCloudApiProperties: BigDataCloudApiProperties,
    private val webClient: WebClient,
) {

    fun reverseGeocoding(latitude: String, longitude: String): Mono<ReverseGeocodingResponse> {
        val baseUrl = "${bigDataCloudApiProperties.url}/data/reverse-geocode-client"
        val parameterizedUrl = UriComponentsBuilder.fromUriString(baseUrl)
            .query("latitude=$latitude")
            .query("longitude=$longitude")
            .toUriString()
        return webClient.get()
            .uri(parameterizedUrl)
            .retrieve()
            .onStatus(
                { it.is4xxClientError || it.is5xxServerError },
                { clientResponse ->
                    clientResponse.bodyToMono(BigDataCloudApiErrorResponse::class.java)
                        .map {
                            ApiException(
                                HttpStatus.valueOf(clientResponse.statusCode().value()),
                                it.description
                            )
                        }
                }
            )
            .bodyToMono(ReverseGeocodingResponse::class.java)
    }

}