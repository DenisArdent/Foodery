package com.denisardent.foodery.model.accounts.entities

data class SignUpData(
    val username: String,
    val email: String,
    var password: String,
)