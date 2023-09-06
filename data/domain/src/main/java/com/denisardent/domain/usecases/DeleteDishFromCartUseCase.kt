package com.denisardent.domain.usecases

import com.denisardent.local.dishes.DishesDataRepository
import javax.inject.Inject

class DeleteDishFromCartUseCase @Inject constructor(
    private val dishesDataRepository: DishesDataRepository
) {
    suspend operator fun invoke(accountId: Long, dishId: Int){
        dishesDataRepository.deleteDish(accountId, dishId.toLong())
    }
}