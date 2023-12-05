package com.ichromanrd.weather.cache

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ichromanrd.weather.cache.model.WeatherCache
import com.ichromanrd.weather.dto.response.WeatherApiResponse
import java.time.Duration
import java.util.function.Supplier
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WeatherCacheService(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, String>
) {

    companion object {
        private val TTL = Duration.ofDays(7)
    }

    fun get(id: String, callback: Supplier<Mono<WeatherApiResponse>>): Mono<WeatherApiResponse> {
        return reactiveRedisOperations.opsForValue()
            .get(id)
            .map { jacksonObjectMapper().readValue(it, WeatherCache::class.java) }
            .map { it.toDto() }
            .switchIfEmpty(
                callback.get().flatMap { set(id, it) }
            )
            .doOnError {
                println(it.message)
            }
    }

    fun set(id: String, value: WeatherApiResponse, ttl: Duration): Mono<WeatherApiResponse> {
        val serializedValue = jacksonObjectMapper().writeValueAsString(WeatherCache.fromDto(id, value))
        return reactiveRedisOperations.opsForValue().set(id,  serializedValue, ttl).map { value }
    }

    fun set(id: String, value: WeatherApiResponse): Mono<WeatherApiResponse> = set(id, value, TTL)

}