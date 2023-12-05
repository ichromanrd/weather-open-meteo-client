package com.ichromanrd.weather.cache

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ichromanrd.weather.cache.model.CityCache
import com.ichromanrd.weather.dto.City
import java.time.Duration
import java.util.function.Supplier
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CityCacheService(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, String>
) {

    companion object {
        private val TTL = Duration.ofDays(7)
    }

    fun get(id: String, callback: Supplier<Mono<City>>): Mono<City> {
        return reactiveRedisOperations.opsForValue()
            .get(id)
            .map { jacksonObjectMapper().readValue(it, CityCache::class.java) }
            .map { it.toDto() }
            .switchIfEmpty(
                callback.get().flatMap { set(id, it) }
            )
            .doOnError {
                println(it.message)
            }
    }

    fun set(id: String, value: City, ttl: Duration): Mono<City> {
        val serializedValue = jacksonObjectMapper().writeValueAsString(CityCache.fromDto(id, value))
        return reactiveRedisOperations.opsForValue().set(id,  serializedValue, ttl).map { value }
    }

    fun set(id: String, value: City): Mono<City> = set(id, value, TTL)

}