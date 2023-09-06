package com.denisardent.local.dishes

import com.denisardent.local.dishes.room.DishDbEntity
import kotlinx.coroutines.flow.Flow

interface DishesDataRepository {
    fun getDishes(accountId: Long): Flow<List<DishDbEntity>>

    suspend fun changeDishesQuantity(accountId: Long,dishId: Long, increasing: Boolean)

    suspend fun deleteDish(accountId: Long, dishId: Long)
}