package com.sehatq.test.domain.models

data class Category (
        val id: Long,
        val name: String,
        val imageUrl: String
) : BaseObject()