package com.ichromanrd.weather.service

import com.ichromanrd.weather.cache.CityCacheService
import com.ichromanrd.weather.cache.model.CityCache
import com.ichromanrd.weather.dto.City
import com.ichromanrd.weather.rest.BigDataCloudApiClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CityService(
    private val bigDataCloudApiClient: BigDataCloudApiClient,
    private val cityCacheService: CityCacheService,
) {

    fun getCity(latitude: String, longitude: String): Mono<City> =
        cityCacheService.get(CityCache.getId(latitude, longitude)) { getCityFromApi(latitude, longitude) }

    private fun getCityFromApi(latitude: String, longitude: String): Mono<City> =
        bigDataCloudApiClient.reverseGeocoding(latitude, longitude)
            .map {
                City(it.locality, it.city, it.countryName)
            }

}