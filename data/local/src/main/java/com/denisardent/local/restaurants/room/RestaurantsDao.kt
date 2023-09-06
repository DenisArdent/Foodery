package com.denisardent.local.restaurants.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurants")
    fun getRestaurants(): List<RestaurantDbEntity>

    @Query("SELECT * FROM account_liked_restaurants_view WHERE account_id = :accountId")
    fun getRestaurantsState(accountId: Long): List<RestaurantsLikedTuple>

    @Query("SELECT is_liked FROM accounts_restaurants_liked WHERE account_id = :accountId AND restaurant_id=:restaurantId")
    fun getRestaurantIsLiked(accountId: Long, restaurantId: Long): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun changeRestaurantIsLikedState(accountRestaurantLikedDbEntity: AccountRestaurantLikedDbEntity)
}