package com.sehatq.test.domain.entities

data class ErrorEntity(
        var resource: String? = null,
        var field: String? = null,
        var code: String? = null
)