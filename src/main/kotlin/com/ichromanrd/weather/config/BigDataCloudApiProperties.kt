package com.ichromanrd.weather.config

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("big-data-cloud-api")
data class BigDataCloudApiProperties(
    val url: String
)
