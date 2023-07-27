package com.denisardent.home.presentation

import androidx.lifecycle.viewModelScope
import com.denisardent.common.ErrorResult
import com.denisardent.common.PendingResult
import com.denisardent.common.SuccessResult
import com.denisardent.common.entities.Restaurant
import com.denisardent.domain.usecases.GetCurrentIdUseCase
import com.denisardent.domain.usecases.GetRestaurantsUseCase

import com.denisardent.presentation.BaseViewModel
import com.denisardent.presentation.MutableResultFlow
import com.denisardent.presentation.ResultFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getCurrentIdUseCase: GetCurrentIdUseCase
): BaseViewModel()
{
    private val _restaurantsList: MutableResultFlow<List<Restaurant>> = MutableStateFlow(PendingResult())
    val restaurantsList: ResultFlow<List<Restaurant>> = _restaurantsList.asStateFlow()

    init {
        loadRestaurants()
    }


    @Suppress("unused")
    fun tryAgain(){
        loadRestaurants()
        _restaurantsList.tryEmit(PendingResult())
    }

    private fun loadRestaurants(){
        viewModelScope.launch {
            try {
                val restaurants = getRestaurantsUseCase(accountId = getCurrentIdUseCase())
                _restaurantsList.tryEmit(SuccessResult(restaurants))
            } catch (e: Exception){
                _restaurantsList.tryEmit(ErrorResult(e))
            }
        }
    }
}
