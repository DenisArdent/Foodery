package com.denisardent.foodery.model.accounts.room

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.util.Log
import com.denisardent.foodery.AuthException
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.model.accounts.entities.Account
import com.denisardent.foodery.model.accounts.entities.SignUpData
import com.denisardent.foodery.preferences.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RoomAccountsRepository(
    val accountsDao: AccountsDao,
    val appPreferences: AppPreferences
): AccountsRepository {

    override fun getCurrentId(): Long {
        return appPreferences.getCurrentId()
    }

    override suspend fun isSignedIn(): Boolean {
        return appPreferences.getCurrentId() != AppPreferences.NO_ACCOUNT_ID
    }

    override suspend fun signIn(email: String, password: String): Boolean = withContext(Dispatchers.IO){
        delay(1000)
        val id = findAccountIdByEmailAndPassword(email, password)
        appPreferences.setCurrentId(id)
        return@withContext true
    }

    override suspend fun signUp(signUpData: SignUpData) {
        accountsDao.createAccount(AccountDbEntity.mapFromSignUpData(signUpData))
    }

    override suspend fun getAccount(accountId: Long): Account? {
        TODO("Not yet implemented")
    }

    private suspend fun findAccountIdByEmailAndPassword(email: String, password: String): Long {
        val tuple = accountsDao.findByEmail(email) ?:throw AuthException()
        if (tuple.password != password) throw AuthException()
        return tuple.id
    }

    override fun logOut() {
        appPreferences.setCurrentId(AppPreferences.NO_ACCOUNT_ID)
    }
}