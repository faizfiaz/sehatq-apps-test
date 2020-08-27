package com.sehatq.test.domain.mappers

import com.sehatq.test.domain.entities.response.ResponseDataHome
import com.sehatq.test.domain.models.Category
import com.sehatq.test.domain.models.DataHome
import com.sehatq.test.domain.models.Product

class UserMapper : BaseMapper<ResponseDataHome?, DataHome?>() {
    override fun createObject(): DataHome? {
        return DataHome()
    }

    override fun createEntity(): ResponseDataHome? {
        return ResponseDataHome()
    }

    override fun defineObject(`object`: DataHome?): DataHome? {
        var listCategory: ArrayList<Category> = ArrayList()
        baseEntity?.data?.category?.forEach {
            listCategory.add(Category(it.id, it.name, it.imageUrl))
        }
        `object`?.category = listCategory

        var listProduct: ArrayList<Product> = ArrayList()
        baseEntity?.data?.productPromo?.forEach {
            listProduct.add(Product(it.id, it.imageUrl, it.title, it.description, it.price, it.loved))
        }
        `object`?.productPromo = listProduct
        return `object`
    }

    override fun defineEntity(entity: ResponseDataHome?): ResponseDataHome? {
        return entity
    }
}