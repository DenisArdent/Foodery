package com.denisardent.local.accounts

import com.denisardent.common.entities.Account
import com.denisardent.common.entities.SignUpData

interface AccountsDataRepository {

    fun getCurrentId(): Long

    /**
     * Checks is signed in the user or not
     */
    suspend fun isSignedIn():Boolean

    /**
     * try sign in user in system
     */
    suspend fun signIn(email: String, password: String): Boolean

    /**
     * Creates new account
     */
    suspend fun signUp(signUpData: SignUpData)

    /**
     * Returns account flow with current account info
     */
    suspend fun getAccount(accountId: Long): Account?

    /**
     * Log out user from system
     */
    fun logOut()
}