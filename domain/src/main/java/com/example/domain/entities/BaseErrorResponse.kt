package com.example.domain.entities

import java.io.Serializable

open class BaseErrorResponse(
    var code: Any? = null,
    val message: String? = null,
    var errors: Errors? = null
) : Serializable

data class Errors(
    val smsCode: String?
)