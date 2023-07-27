package com.denisardent.domain.usecases

import com.denisardent.common.entities.DishInfo
import com.denisardent.network.DishesNetworkRepository
import javax.inject.Inject

class GetDishesUseCase @Inject constructor(private val dishesNetworkRepository: DishesNetworkRepository) {
    suspend operator fun invoke(): List<DishInfo>{
        return dishesNetworkRepository.getDishesList().map { dishResponse ->
            DishInfo(
                dishResponse.id,
                dishResponse.name,
                dishResponse.price,
                dishResponse.weight,
                dishResponse.description,
                dishResponse.imageUrl
            )
        }
    }
}