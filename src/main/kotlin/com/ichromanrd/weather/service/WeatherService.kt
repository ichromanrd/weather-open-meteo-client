package com.ichromanrd.weather.service

import com.ichromanrd.weather.cache.WeatherCacheService
import com.ichromanrd.weather.cache.model.WeatherCache
import com.ichromanrd.weather.common.constants.WeatherCodes
import com.ichromanrd.weather.dto.request.WeatherApiRequestParam
import com.ichromanrd.weather.dto.response.WeatherApiResponse
import com.ichromanrd.weather.rest.OpenMeteoApiClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WeatherService(
    private val openMeteoApiClient: OpenMeteoApiClient,
    private val cityService: CityService,
    private val weatherCacheService: WeatherCacheService,
) {

    fun getCurrentWeather(requestParam: WeatherApiRequestParam): Mono<WeatherApiResponse> {
        return Mono.just(requestParam)
            .doOnNext { requestParam.validateLatLong() }
            .flatMap {
                weatherCacheService.get(
                    WeatherCache.getId(requestParam.latitude!!, requestParam.longitude!!, requestParam.timezone!!)
                ) { getCurrentWeatherFromApi(requestParam) }
            }
    }

    fun getCurrentWeatherFromApi(requestParam: WeatherApiRequestParam): Mono<WeatherApiResponse> {
        return openMeteoApiClient.currentWeather(
            requestParam.latitude,
            requestParam.longitude,
            requestParam.timezone
        ).flatMap { weatherResponse ->
            cityService.getCity(
                requestParam.latitude!!,
                requestParam.longitude!!
            ).map {
                WeatherApiResponse(
                    "${weatherResponse.current?.time!!} ${weatherResponse.timezoneAbbreviation}",
                    "${weatherResponse.current.apparentTemp!!} ${weatherResponse.currentUnits?.apparentTemp!!}",
                    WeatherCodes.from(weatherResponse.current.weatherCode!!)!!.description,
                    "${it.name}, ${it.region}, ${it.country}"
                )
            }
        }
    }

}