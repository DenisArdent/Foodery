package com.denisardent.foodery.preferences

interface AppPreferences {
    fun setCurrentId(accountId: Long)

    fun getCurrentId(): Long

    companion object{
        const val NO_ACCOUNT_ID = -12345L
    }
}