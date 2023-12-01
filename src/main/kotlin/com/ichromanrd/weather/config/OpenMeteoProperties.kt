package com.ichromanrd.weather.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("open-meteo-api")
data class OpenMeteoProperties(
    val url: Map<String, String> = emptyMap()
)