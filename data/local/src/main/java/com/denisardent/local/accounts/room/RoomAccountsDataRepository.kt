package com.denisardent.local.accounts.room

import com.denisardent.common.AuthException
import com.denisardent.common.entities.Account
import com.denisardent.local.accounts.AccountsDataRepository
import com.denisardent.local.preferences.AppPreferences
import com.denisardent.local.security.SecurityUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomAccountsDataRepository @Inject constructor(
    val accountsDao: AccountsDao,
    val appPreferences: AppPreferences,
    val securityUtils: SecurityUtils
): AccountsDataRepository {

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

    override suspend fun signUp(signUpData: com.denisardent.common.entities.SignUpData) {
        delay(1000)
        accountsDao.createAccount(AccountDbEntity.mapFromSignUpData(signUpData, securityUtils))
    }

    override suspend fun getAccount(accountId: Long): Account? {
        TODO("Not yet implemented")
    }

    private suspend fun findAccountIdByEmailAndPassword(email: String, password: String): Long {
        val tuple = accountsDao.findByEmail(email) ?:throw AuthException()
        val saltString = tuple.salt
        val hashBytes = securityUtils.passwordToHash(password.toCharArray(), securityUtils.stringToBytes(saltString))
        val hashString = securityUtils.bytesToString(hashBytes)
        if (tuple.hash != hashString) throw AuthException()
        return tuple.id
    }

    override fun logOut() {
        appPreferences.setCurrentId(AppPreferences.NO_ACCOUNT_ID)
    }
}