package com.denisardent.home.presentation.home.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.common.ErrorResult
import com.denisardent.common.PendingResult
import com.denisardent.common.SuccessResult
import com.denisardent.common.entities.DishInfo
import com.denisardent.common.entities.Restaurant
import com.denisardent.domain.usecases.ChangeDishQuantityUseCase
import com.denisardent.domain.usecases.ChangeRestaurantStateUseCase
import com.denisardent.domain.usecases.GetCurrentIdUseCase
import com.denisardent.domain.usecases.GetDishesUseCase
import com.denisardent.domain.usecases.GetRestaurantsUseCase
import com.denisardent.presentation.MutableResultFlow
import com.denisardent.presentation.ResultFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val getCurrentIdUseCase: GetCurrentIdUseCase,
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getDishesUseCase: GetDishesUseCase,
    private val changeDishQuantityUseCase: ChangeDishQuantityUseCase,
    private val changeRestaurantStateUseCase: ChangeRestaurantStateUseCase
): ViewModel() {
    private val _state: MutableResultFlow<RestaurantState> = MutableStateFlow(PendingResult())
    val state: ResultFlow<RestaurantState> = _state.asStateFlow()


    suspend fun getCurrentRestaurant(id: Int){
        viewModelScope.launch{
            try {
                _state.tryEmit(
                    SuccessResult(
                        RestaurantState(
                            dishList = getDishesUseCase(),
                            restaurantInfo = getRestaurantsUseCase(getCurrentIdUseCase())[id]
                        )
                    )
                )
            } catch (e: Exception){
//                _restaurantInfo.tryEmit(ErrorResult(e))
                _state.tryEmit(ErrorResult(e))
            }
        }
    }

    fun changeRestaurantState(isLiked: Boolean, restaurantId: Long){
        viewModelScope.launch {
            changeRestaurantStateUseCase(isLiked, restaurantId)
        }
    }

    fun onDishAdded(id: Int){
        viewModelScope.launch {
            changeDishQuantityUseCase(getCurrentIdUseCase(), id, true)
        }
    }


}

data class RestaurantState(
    val dishList: List<DishInfo>,
    val restaurantInfo: Restaurant
)


