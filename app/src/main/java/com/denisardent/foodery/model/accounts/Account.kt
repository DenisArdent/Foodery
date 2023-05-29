package com.denisardent.foodery.model.accounts

/**
 * Data class which defines account's properties.
 */

data class Account(
    val id: Long,
    val email: String,
    val phoneNumber: String?,
    val username: String,
    val createdAt: Long
)
