package com.sehatq.test.domain.entities

data class CategoriesEntities (
        val id: Long,
        val name: String,
        val imageUrl: String
) : BaseObjectEntity()