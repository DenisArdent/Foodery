package com.denisardent.local.restaurants

import com.denisardent.common.entities.Restaurant
import com.denisardent.theme.R

/**
 * Interface to interact with restaurant repository
 */
interface RestaurantsDataRepository {

    /**
     * Suspend function that returns restaurants list, when it ready
     */
    fun getSelectedRestaurantState(accountId: Long, restaurantId: Long): Boolean

    suspend fun getRestaurants(accountId: Long): List<Restaurant>

    suspend fun changeRestaurantState(accountId: Long, restaurantId: Long, isLiked: Boolean)

    companion object{
        val LOGOS =  listOf(R.drawable.ic_first_restaurant, R.drawable.ic_second_restaurant, R.drawable.ic_third_restaurant, R.drawable.ic_fourth_restaurant, R.drawable.foodery)

    }
}