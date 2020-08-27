package com.sehatq.test.domain.entities.response

import com.sehatq.test.domain.entities.BaseObjectEntity
import com.sehatq.test.domain.entities.CategoriesEntities
import com.sehatq.test.domain.entities.ProductEntities

data class ResponseDataHome(
        val data: Data? = null
) : BaseObjectEntity()

data class Data(
        val category: List<CategoriesEntities>? = null,
        val productPromo: List<ProductEntities>? = null
)