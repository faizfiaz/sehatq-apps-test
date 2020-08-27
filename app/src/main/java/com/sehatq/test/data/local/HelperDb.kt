package com.sehatq.test.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sehatq.test.App
import com.sehatq.test.data.local.table.TableProduct

class HelperDb constructor(context: Context?, private val dbName: String)
    : SQLiteOpenHelper(context, dbName, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TableProduct.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        var DATABASE_VERSION = 1
        var instance: HelperDb? = null
            get() {
                val context: Context? = App.appContext
                if (field == null) {
                    field = HelperDb(context, "sehatq.db")
                }
                return field
            }
            private set
    }

}