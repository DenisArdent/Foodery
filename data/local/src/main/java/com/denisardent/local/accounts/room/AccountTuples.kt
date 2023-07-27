package com.denisardent.local.accounts.room

data class AccountSignInTuple(
    val id: Long,
    val hash: String,
    val salt: String
)