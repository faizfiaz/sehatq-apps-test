package com.sehatq.test.domain.models

import java.io.Serializable

data class Product(
        val id: String,
        val imageUrl: String,
        val title: String,
        val description: String,
        val price: String,
        var loved: Int,
        var qty: Int = 0
) : BaseObject(), Serializable