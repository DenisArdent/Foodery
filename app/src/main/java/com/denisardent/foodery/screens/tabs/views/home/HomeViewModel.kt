package com.denisardent.foodery.screens.tabs.views.home

import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.model.*
import com.denisardent.foodery.model.accounts.entities.Restaurant
import com.denisardent.foodery.model.restaurant.RestaurantRepository
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.utils.base.BaseViewModel
import com.denisardent.foodery.utils.base.MutableResultFlow
import com.denisardent.foodery.utils.base.ResultFlow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HomeViewModel(private val restaurantRepository: RestaurantRepository,
                    private val accountsRepository: AccountsRepository): BaseViewModel() {

    private val _restaurantsList: MutableResultFlow<List<Restaurant>> = MutableStateFlow(PendingResult())
    val restaurantsList: ResultFlow<List<Restaurant>> = _restaurantsList.asStateFlow()

    init {
        loadRestaurants()
    }


    fun tryAgain(){
        loadRestaurants()
        _restaurantsList.tryEmit(PendingResult())
    }

    private fun loadRestaurants(){
        viewModelScope.launch {
            try {
                restaurantRepository.getRestaurantsFlow(accountId = accountsRepository.getCurrentId()).collectLatest {

                    _restaurantsList.tryEmit(SuccessResult(it))
                }
            } catch (e: Exception){
                _restaurantsList.tryEmit(ErrorResult(e))
            }
        }
    }
}
