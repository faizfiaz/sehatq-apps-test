package com.sehatq.test.domain.models

data class DataHome(
        var category: List<Category>? = null,
        var productPromo: List<Product>? = null
) : BaseObject()