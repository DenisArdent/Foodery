package com.denisardent.foodery.model.restaurant

/**
 * Interface to interact with restaurant repository
 */
interface RestaurantRepository {

    /**
     * Suspend function that returns restaurants list, when it ready
     */
    suspend fun getRestaurants(): List<Restaurant>
}