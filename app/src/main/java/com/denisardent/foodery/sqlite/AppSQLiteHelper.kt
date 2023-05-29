package com.denisardent.foodery.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppSQLiteHelper(private val appContext: Context):
    SQLiteOpenHelper(appContext, "database.db", null, 1)
{
    override fun onCreate(db: SQLiteDatabase) {
        val sqlQuery = appContext.assets.open("db_init.sql").bufferedReader().use {
            it.readText()
        }
        sqlQuery.split(";")
            .filter { it.isNotBlank() }
            .forEach { db.execSQL(it) }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}