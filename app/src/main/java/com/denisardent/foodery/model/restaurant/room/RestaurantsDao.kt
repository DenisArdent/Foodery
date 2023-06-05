package com.denisardent.foodery.model.restaurant.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.denisardent.foodery.model.accounts.entities.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurants")
    fun getRestaurants(): Flow<List<RestaurantDbEntity>>

    @Query("SELECT * FROM account_liked_restaurants_view WHERE account_id = :accountId")
    fun getRestaurantsState(accountId: Long): Flow<List<RestaurantsLikedTuple>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun changeRestaurantIsLikedState(accountRestaurantLikedDbEntity: AccountRestaurantLikedDbEntity)
}