package com.denisardent.local.preferences

import android.app.Application
import android.content.Context
import com.denisardent.local.preferences.AppPreferences.Companion.NO_ACCOUNT_ID
import javax.inject.Inject

class AppSharedPreferences @Inject constructor(appContext: Application): AppPreferences {

    private val sharedPreferences = appContext.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)

    override fun setCurrentId(accountId: Long) {
        sharedPreferences.edit()
            .putLong(APP_PREFERENCES_ID, accountId)
            .apply()
    }

    override fun getCurrentId(): Long {
        return sharedPreferences.getLong(APP_PREFERENCES_ID, NO_ACCOUNT_ID)
    }

    companion object{
        const val APP_SETTINGS = "settings"
        const val APP_PREFERENCES_ID = "account_id"
    }
}