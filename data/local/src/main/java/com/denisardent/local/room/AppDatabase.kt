package com.denisardent.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.denisardent.local.accounts.room.AccountDbEntity
import com.denisardent.local.accounts.room.AccountsDao
import com.denisardent.local.dishes.room.DishDbEntity
import com.denisardent.local.dishes.room.DishesDao
import com.denisardent.local.restaurants.room.AccountRestaurantLikedDbEntity
import com.denisardent.local.restaurants.room.RestaurantDbEntity
import com.denisardent.local.restaurants.room.RestaurantsDao
import com.denisardent.local.restaurants.room.views.AccountLikedRestaurantsView

@Database(
    entities = [
        AccountDbEntity::class,
        RestaurantDbEntity::class,
        AccountRestaurantLikedDbEntity::class,
        DishDbEntity::class
    ],
    version = 1,
    views = [
        AccountLikedRestaurantsView::class
    ],
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

    abstract fun getRestaurantsDao(): RestaurantsDao

    abstract fun getDishesDao(): DishesDao
}