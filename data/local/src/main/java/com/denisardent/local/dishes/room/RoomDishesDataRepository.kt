package com.denisardent.local.dishes.room

import com.denisardent.local.dishes.DishesDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomDishesDataRepository @Inject constructor(
    private val dishesDao: DishesDao
): DishesDataRepository {
    override fun getDishes(accountId: Long): Flow<List<DishDbEntity>> {
        return dishesDao.getDishes(accountId).map {
            it
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun changeDishesQuantity(accountId: Long, dishId: Long, increasing: Boolean) {
        val addedValue = if (increasing) 1 else -1
        withContext(Dispatchers.IO){
            val oldValue = dishesDao.getDishesQuantity(accountId, dishId)
            val newValue = oldValue + addedValue
            if (newValue <= 0){
                dishesDao.deleteDishDbEntity(accountId, dishId)
            } else{
                dishesDao.changeDishesQuantity(
                    DishDbEntity(accountId, dishId, oldValue+addedValue)
                )
            }
        }
    }

    override suspend fun deleteDish(accountId: Long, dishId: Long) {
        withContext(Dispatchers.IO){
            dishesDao.deleteDishDbEntity(accountId, dishId)
        }
    }
}