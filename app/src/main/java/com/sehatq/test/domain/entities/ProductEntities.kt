package com.sehatq.test.domain.entities

data class ProductEntities(
        val id: String,
        val imageUrl: String,
        val title: String,
        val description: String,
        val price: String,
        val loved: Int
): BaseObjectEntity()