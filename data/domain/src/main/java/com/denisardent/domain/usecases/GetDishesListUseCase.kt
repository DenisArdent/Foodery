package com.denisardent.domain.usecases

import com.denisardent.common.entities.DishItem
import com.denisardent.local.dishes.DishesDataRepository
import com.denisardent.network.DishesNetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDishesListUseCase @Inject constructor(
    private val dishesNetworkRepository: DishesNetworkRepository,
    private val dishesDataRepository: DishesDataRepository
) {
    suspend operator fun invoke(accountId: Long): Flow<List<DishItem>> {
        val dishesInfoList =  dishesNetworkRepository.getDishesList()

        return dishesDataRepository.getDishes(accountId).map{ list ->
            list.mapNotNull { dishDbEntity ->
                if (dishDbEntity.quantity == 0) return@mapNotNull null
                val dishInfo = dishesInfoList.first {
                    it.id == dishDbEntity.dishId.toInt()
                }
                DishItem(
                    id = dishDbEntity.dishId.toInt(),
                    quantity = dishDbEntity.quantity,
                    name = dishInfo.name,
                    price =  dishInfo.price,
                    description = dishInfo.description,
                    imageUrl = dishInfo.imageUrl,
                    weight = dishInfo.weight
                )
            }
        }
    }
}