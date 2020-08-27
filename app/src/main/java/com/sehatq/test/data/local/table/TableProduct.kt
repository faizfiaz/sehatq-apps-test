package com.sehatq.test.data.local.table

object TableProduct {
    const val TABLE_NAME = "product"
    const val COLUMN_ID = "id"
    const val COLUMN_IMAGE = "image"
    const val COLUMN_NAME = "name"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_QTY = "qty"
    const val COLUMN_PRICE = "price"
    const val COLUMN_LOVED = "loved"

    const val SQL_CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " VARCHAR, "
            + COLUMN_IMAGE + " VARCHAR NOT NULL, "
            + COLUMN_NAME + " VARCHAR NOT NULL, "
            + COLUMN_DESCRIPTION + " VARCHAR NOT NULL, "
            + COLUMN_PRICE + " BIGINT NOT NULL, "
            + COLUMN_QTY + " BIGINT NOT NULL, "
            + COLUMN_LOVED + " SMALLINT NOT NULL "
            + ")")
}