package com.ichromanrd.weather.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class RestClientConfig {

    @Bean
    fun webClient(): WebClient =
        WebClient.builder().build()

}