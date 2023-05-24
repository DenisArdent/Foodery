package com.denisardent.foodery.tabs.views.home

import com.denisardent.foodery.model.*
import com.denisardent.foodery.model.restaurant.Restaurant
import com.denisardent.foodery.model.restaurant.RestaurantRepository
import com.denisardent.foodery.tabs.views.BaseViewModel
import com.denisardent.foodery.tabs.views.LiveResult
import com.denisardent.foodery.tabs.views.MutableLiveResult


class HomeViewModel(val restaurantRepository: RestaurantRepository): BaseViewModel() {

    private val _restaurantsLiveData = MutableLiveResult<List<Restaurant>>(PendingResult())
    val restaurantsLiveData: LiveResult<List<Restaurant>> = _restaurantsLiveData

    init {
        load()
    }

    private fun load() = into(_restaurantsLiveData){restaurantRepository.getRestaurants()}

    fun tryAgain(){
        load()
        _restaurantsLiveData.value = PendingResult()
    }
}
