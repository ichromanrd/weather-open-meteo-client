package com.ichromanrd.weather.common.errors

import com.ichromanrd.weather.dto.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import reactor.core.publisher.Mono

@ControllerAdvice
class GlobalControllerAdvice {
    @ExceptionHandler(value = [ApiException::class])
    fun apiExceptionHandler(exception: ApiException): Mono<ResponseEntity<Any>> =
        Mono.just(
            ResponseEntity(
                ErrorResponse(exception.message),
                exception.status
            )
        )
}