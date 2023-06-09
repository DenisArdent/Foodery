package com.denisardent.foodery

import android.app.Application
import androidx.room.Room
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.model.accounts.room.RoomAccountsRepository
import com.denisardent.foodery.model.restaurant.RestaurantRepository
import com.denisardent.foodery.model.restaurant.room.RoomRestaurantRepository
import com.denisardent.foodery.model.room.AppDatabase
import com.denisardent.foodery.preferences.AppPreferences
import com.denisardent.foodery.preferences.AppSharedPreferences
import com.denisardent.foodery.utils.security.SecurityUtils
import com.denisardent.foodery.utils.security.SecurityUtilsSimpleImpl

class App: Application() {
    lateinit var restaurantRepository: RestaurantRepository
    lateinit var accountsRepository: AccountsRepository
    lateinit var securityUtils: SecurityUtils
    private val appPreferences: AppPreferences by lazy {
        AppSharedPreferences(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        val appContext = applicationContext
        securityUtils = SecurityUtilsSimpleImpl()
        val roomDatabase =  Room.databaseBuilder(appContext, AppDatabase::class.java, "roomdatabase.db")
            .createFromAsset("db_init.db")
            .build()
        accountsRepository = RoomAccountsRepository(roomDatabase.getAccountsDao(), appPreferences,
            securityUtils)
        restaurantRepository = RoomRestaurantRepository(roomDatabase.getRestaurantsDao())
    }
}