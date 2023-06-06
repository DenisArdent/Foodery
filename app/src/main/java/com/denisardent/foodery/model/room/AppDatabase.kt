package com.denisardent.foodery.model.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.denisardent.foodery.model.accounts.room.AccountDbEntity
import com.denisardent.foodery.model.accounts.room.AccountsDao
import com.denisardent.foodery.model.restaurant.room.AccountRestaurantLikedDbEntity
import com.denisardent.foodery.model.restaurant.room.RestaurantDbEntity
import com.denisardent.foodery.model.restaurant.room.RestaurantsDao
import com.denisardent.foodery.model.restaurant.room.views.AccountLikedRestaurantsView

@Database(
    entities = [
        AccountDbEntity::class,
        RestaurantDbEntity::class,
        AccountRestaurantLikedDbEntity::class
    ],
    version = 2,
    views = [
        AccountLikedRestaurantsView::class
    ],
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = AutoMigrationSpec1To2::class)
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

    abstract fun getRestaurantsDao(): RestaurantsDao
}