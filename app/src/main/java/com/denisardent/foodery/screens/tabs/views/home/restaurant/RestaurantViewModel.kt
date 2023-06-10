package com.denisardent.foodery.screens.tabs.views.home.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.model.ErrorResult
import com.denisardent.foodery.model.PendingResult
import com.denisardent.foodery.model.SuccessResult
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.model.accounts.entities.Restaurant
import com.denisardent.foodery.model.restaurant.RestaurantRepository
import com.denisardent.foodery.utils.base.MutableResultFlow
import com.denisardent.foodery.utils.base.ResultFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RestaurantViewModel(private val restaurantsRepository: RestaurantRepository,
private val accountsRepository: AccountsRepository): ViewModel() {
    private val _restaurantInfo: MutableResultFlow<Restaurant> =  MutableStateFlow(PendingResult())
    val restaurantInfo: ResultFlow<Restaurant> = _restaurantInfo.asStateFlow()



    suspend fun getCurrentRestaurant(id: Int){
        viewModelScope.launch{
            try {
                restaurantsRepository
                restaurantsRepository.getRestaurantsFlow(accountsRepository.getCurrentId()).collectLatest {
                    _restaurantInfo.tryEmit(SuccessResult(it[id]))
                }
            } catch (e: Exception){
                _restaurantInfo.tryEmit(ErrorResult(e))
            }
        }
    }

    fun changeRestaurantState(isLiked: Boolean, restaurantId: Long){
        viewModelScope.launch {
            restaurantsRepository.changeRestaurantState(accountsRepository.getCurrentId(), restaurantId, isLiked)
        }
    }
}
