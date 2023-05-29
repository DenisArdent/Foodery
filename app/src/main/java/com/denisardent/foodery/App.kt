package com.denisardent.foodery

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.model.accounts.SQLAccountsRepository
import com.denisardent.foodery.model.restaurant.RestaurantRepository
import com.denisardent.foodery.model.restaurant.SQLiteRestaurantRepository
import com.denisardent.foodery.preferences.AppPreferences
import com.denisardent.foodery.sqlite.AppSQLiteHelper

class App: Application() {
    lateinit var restaurantRepository: RestaurantRepository
    lateinit var accountsRepository: AccountsRepository
    private val appPreferences: AppPreferences by lazy {
        AppSharedPreferences(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        val appContext = applicationContext
        val database = AppSQLiteHelper(appContext).writableDatabase
        accountsRepository = SQLAccountsRepository(database, appPreferences)
        restaurantRepository = SQLiteRestaurantRepository(database)
    }
}