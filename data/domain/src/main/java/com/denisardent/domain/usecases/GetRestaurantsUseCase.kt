package com.denisardent.domain.usecases

import com.denisardent.common.entities.Restaurant
import com.denisardent.local.restaurants.RestaurantsDataRepository
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val restaurantsDataRepository: RestaurantsDataRepository
) {
    suspend operator fun invoke(
        accountId: Long
    ): List<Restaurant>{
        return restaurantsDataRepository.getRestaurants(accountId)
    }
}