package com.denisardent.domain.usecases

import com.denisardent.local.restaurants.RestaurantsDataRepository
import javax.inject.Inject

class ChangeRestaurantStateUseCase @Inject constructor(
    private val restaurantsDataRepository: RestaurantsDataRepository,
    private val getCurrentIdUseCase: GetCurrentIdUseCase
){
    suspend operator fun invoke(isLiked: Boolean, restaurantId: Long){
        restaurantsDataRepository.changeRestaurantState(getCurrentIdUseCase(), isLiked = isLiked, restaurantId = restaurantId)
    }
}