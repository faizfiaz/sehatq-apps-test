package com.sehatq.test.data.local.dao

import android.content.ContentValues
import android.content.Context
import com.sehatq.test.data.local.table.TableProduct
import com.sehatq.test.domain.models.Product
import javax.inject.Singleton

class DaoProduct(context: Context) : BaseDao<Product?>(context) {

    override fun values(t: Product?): ContentValues? {
        val values = ContentValues()
        try {
            values.put(TableProduct.COLUMN_ID, t?.id)
            values.put(TableProduct.COLUMN_IMAGE, t?.imageUrl)
            values.put(TableProduct.COLUMN_NAME, t?.title)
            values.put(TableProduct.COLUMN_DESCRIPTION, t?.description)
            values.put(TableProduct.COLUMN_QTY, t?.qty)
            values.put(TableProduct.COLUMN_PRICE, t?.price)
            values.put(TableProduct.COLUMN_LOVED, t?.loved)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return values
    }

    override fun cursorData(): Product {
        return Product(
                sourceCursor.getCursorString(TableProduct.COLUMN_ID),
                sourceCursor.getCursorString(TableProduct.COLUMN_IMAGE),
                sourceCursor.getCursorString(TableProduct.COLUMN_NAME),
                sourceCursor.getCursorString(TableProduct.COLUMN_DESCRIPTION),
                sourceCursor.getCursorString(TableProduct.COLUMN_PRICE),
                sourceCursor.getCursorInt(TableProduct.COLUMN_LOVED),
                sourceCursor.getCursorInt(TableProduct.COLUMN_QTY)
        )
    }

    init {
        tableName = TableProduct.TABLE_NAME
        allColumn = arrayOf(
                TableProduct.COLUMN_ID,
                TableProduct.COLUMN_IMAGE,
                TableProduct.COLUMN_NAME,
                TableProduct.COLUMN_DESCRIPTION,
                TableProduct.COLUMN_PRICE,
                TableProduct.COLUMN_LOVED,
                TableProduct.COLUMN_QTY
        )
    }
}