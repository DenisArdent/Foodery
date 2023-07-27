package com.denisardent.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitDishesRepository @Inject constructor(private val dishesApi: DishesApi): DishesNetworkRepository {
    override suspend fun getDishesList(): List<DishResponse> {
        return withContext(Dispatchers.IO){
            dishesApi.getDishes()
        }
    }
}