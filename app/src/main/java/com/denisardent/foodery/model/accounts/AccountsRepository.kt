package com.denisardent.foodery.model.accounts

import com.denisardent.foodery.model.accounts.entities.Account
import com.denisardent.foodery.model.accounts.entities.SignUpData
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {

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