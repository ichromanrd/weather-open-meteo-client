package com.ichromanrd.weather.service

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
) {

    fun getCurrentWeather(requestParam: WeatherApiRequestParam): Mono<WeatherApiResponse> {
        return Mono.just(requestParam)
            .doOnNext { requestParam.validateLatLong() }
            .map { it }
            .flatMap {
                cityService.getCity(requestParam.latitude!!, requestParam.longitude!!)
            }
            .flatMap { city ->
                openMeteoApiClient.currentWeather(
                    requestParam.latitude,
                    requestParam.longitude,
                    requestParam.timezone
                ).map {
                    WeatherApiResponse(
                        "${it.current?.time!!} ${it.timezoneAbbreviation}",
                        "${it.current.apparentTemp!!} ${it.currentUnits?.apparentTemp!!}",
                        WeatherCodes.from(it.current.weatherCode!!)!!.description,
                        "${city.name}, ${city.region}, ${city.country}"
                    )
                }
            }

    }

}