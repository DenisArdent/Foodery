package com.denisardent.foodery.model.room

import android.app.Application
import androidx.core.content.contentValuesOf
import androidx.room.OnConflictStrategy
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.denisardent.foodery.App
import com.denisardent.foodery.utils.security.SecurityUtilsSimpleImpl

@RenameColumn(
    tableName = "accounts",
    fromColumnName = "password",
    toColumnName = "hash"
)
class AutoMigrationSpec1To2: AutoMigrationSpec{
    val securityUtils = SecurityUtilsSimpleImpl()

    override fun onPostMigrate(db: SupportSQLiteDatabase) {
        super.onPostMigrate(db)
        db.query("SELECT * FROM accounts").use { cursor ->
            val idIndex = cursor.getColumnIndex("id")
            val passwordIndex = cursor.getColumnIndex("hash")
            while (cursor.moveToNext()){
                val id = cursor.getLong(idIndex)
                val password = cursor.getString(passwordIndex)
                val salt = securityUtils.generateSalt()
                val hashBytes = securityUtils.passwordToHash(password.toCharArray(), salt)
                db.update(
                    "accounts",
                    OnConflictStrategy.REPLACE,
                    contentValuesOf(
                        "hash" to securityUtils.bytesToString(hashBytes),
                        "salt" to securityUtils.bytesToString(salt)
                    ),
                    "id = ?",
                    arrayOf(id.toString())
                )
            }
        }
    }
}