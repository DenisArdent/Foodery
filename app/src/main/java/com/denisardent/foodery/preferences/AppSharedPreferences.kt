package com.denisardent.foodery.preferences

import android.content.Context
import android.util.Log
import com.denisardent.foodery.preferences.AppPreferences.Companion.NO_ACCOUNT_ID

class AppSharedPreferences(appContext: Context): AppPreferences{

    private val sharedPreferences = appContext.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)

    override fun setCurrentId(accountId: Long) {
        Log.d("NEW_ACCOUNT_ID", "$accountId")
        sharedPreferences.edit()
            .putLong(APP_PREFERENCES_ID, accountId)
            .apply()
    }

    override fun getCurrentId(): Long {
        val currentId = sharedPreferences.getLong(APP_PREFERENCES_ID, NO_ACCOUNT_ID)
        Log.d("GET_CURRENT_ID", "$currentId")
        return currentId
    }

    companion object{
        const val APP_SETTINGS = "settings"
        const val APP_PREFERENCES_ID = "account_id"
    }
}