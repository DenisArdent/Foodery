package com.denisardent.domain.usecases

import com.denisardent.local.dishes.DishesDataRepository
import javax.inject.Inject

class ChangeDishQuantityUseCase @Inject constructor(
    private val dishesDataRepository: DishesDataRepository
) {
    suspend operator fun invoke(accountId: Long, dishId: Int, increasing: Boolean){
        dishesDataRepository.changeDishesQuantity(accountId, dishId.toLong(), increasing)
    }
}