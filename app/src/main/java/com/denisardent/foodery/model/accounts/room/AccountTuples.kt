package com.denisardent.foodery.model.accounts.room

import androidx.room.PrimaryKey

data class AccountSignInTuple(
    val id: Long,
    val password: String
)