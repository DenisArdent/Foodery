package com.denisardent.foodery.model.accounts

import kotlinx.coroutines.flow.Flow

class SQLAccountsRepository:AccountsRepository {

    override suspend fun isSignedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signUp() {
        TODO("Not yet implemented")
    }

    override fun getAccountInfo(): Flow<Account?> {
        TODO("Not yet implemented")
    }
}