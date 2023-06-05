package com.denisardent.foodery.model.restaurant

import com.denisardent.foodery.R
import com.denisardent.foodery.model.accounts.entities.Restaurant
import kotlinx.coroutines.flow.Flow

/**
 * Interface to interact with restaurant repository
 */
interface RestaurantRepository {

    /**
     * Suspend function that returns restaurants list, when it ready
     */
    fun getSelectedRestaurantState(accountId: Long, restaurantId: Long): Flow<Boolean>

    fun getRestaurantsFlow(accountId: Long): Flow<List<Restaurant>>

    suspend fun changeRestaurantState(accountId: Long, restaurantId: Long, isLiked: Boolean)

    companion object{
        val LOGOS =  listOf(R.drawable.ic_first_restaurant, R.drawable.ic_second_restaurant, R.drawable.ic_third_restaurant, R.drawable.ic_fourth_restaurant, R.drawable.foodery)

    }
}