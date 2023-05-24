package com.denisardent.foodery.model.accounts

import kotlinx.coroutines.flow.Flow

interface AccountsRepository {

    /**
     * Checks is signed in the user or not
     */
    suspend fun isSignedIn():Boolean

    /**
     * try sign in user in system
     */
    suspend fun signIn(email: String, password: String)

    /**
     * Creates new account
     */
    suspend fun signUp()

    /**
     * Returns account flow with current account info
     */
    fun getAccountInfo(): Flow<Account?>
}