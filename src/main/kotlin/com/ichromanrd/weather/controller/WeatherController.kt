package com.ichromanrd.weather.controller

import com.ichromanrd.weather.dto.request.WeatherApiRequestParam
import com.ichromanrd.weather.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/weather")
class WeatherController(
    private val weatherService: WeatherService,
) {

    @GetMapping("/now")
    fun getCurrentWeather(requestParam: WeatherApiRequestParam) =
        weatherService.getCurrentWeather(requestParam)

}