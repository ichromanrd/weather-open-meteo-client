package com.ichromanrd.weather.common.errors

import org.springframework.http.HttpStatus

class ApiException(
    val status: HttpStatus,
    override val message: String
) : RuntimeException()