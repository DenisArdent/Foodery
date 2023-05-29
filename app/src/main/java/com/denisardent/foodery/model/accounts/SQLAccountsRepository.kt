package com.denisardent.foodery.model.accounts

import android.database.sqlite.SQLiteDatabase
import com.denisardent.foodery.AuthException
import com.denisardent.foodery.preferences.AppPreferences
import com.denisardent.foodery.sqlite.AppSQLiteContract
import com.denisardent.foodery.sqlite.AppSQLiteContract.AccountsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SQLAccountsRepository(
    val database: SQLiteDatabase,
    val appPreferences: AppPreferences
    ):AccountsRepository {

    override suspend fun isSignedIn(): Boolean {
        return appPreferences.getCurrentId() != AppPreferences.NO_ACCOUNT_ID
    }

    override suspend fun signIn(email: String, password: String): Boolean =
        withContext(Dispatchers.IO){
            val id = findAccountIdByEmailAndPassword(email, password)
            appPreferences.setCurrentId(id)
            return@withContext true
        }


    override suspend fun signUp() {
        TODO("Not yet implemented")
    }

    override fun getAccountInfo(): Flow<Account?> {
        TODO("Not yet implemented")
    }

    override fun logOut() {
        appPreferences.setCurrentId(AppPreferences.NO_ACCOUNT_ID)
    }

    private fun findAccountIdByEmailAndPassword(email: String, password: String): Long{
        val cursor = database.query(
            AccountsTable.TABLE_NAME,
            arrayOf(AccountsTable.COLUMN_ID, AccountsTable.COLUMN_PASSWORD),
            "${AccountsTable.COLUMN_EMAIL} = ?",
            arrayOf(email),
            null, null, null
        )

        return cursor.use {
            if (cursor.count == 0) throw AuthException()
            cursor.moveToFirst()

            val dbPassword = cursor.getString(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_PASSWORD))
            if (dbPassword != password) throw AuthException()

            cursor.getLong(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_ID))
        }
    }

    private fun getAccountById(id: Long): Account?{
        val cursor = database.query(
            AccountsTable.TABLE_NAME,
            arrayOf(AccountsTable.COLUMN_ID, AccountsTable.COLUMN_EMAIL, AccountsTable.COLUMN_USERNAME, AccountsTable.COLUMN_CREATED_AT, AccountsTable.COLUMN_PHONE_NUMBER),
            "${AccountsTable.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null, null,null
        )

        return cursor.use {
            if (cursor.count == 0) return@use null
            cursor.moveToFirst()
            Account(
                id = cursor.getLong(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_ID)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_EMAIL)),
                username = cursor.getString(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_USERNAME)),
                createdAt = cursor.getLong(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_CREATED_AT)),
                phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(AccountsTable.COLUMN_PHONE_NUMBER))
            )
        }
    }
}