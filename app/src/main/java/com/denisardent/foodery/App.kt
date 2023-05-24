package com.denisardent.foodery

import android.app.Application
import com.denisardent.foodery.model.restaurant.RestaurantRepository
import com.denisardent.foodery.model.restaurant.SimpleRestaurantRepository

class App: Application() {
    // set application as start point of initialization repositories
    val restaurantRepository = SimpleRestaurantRepository()
}