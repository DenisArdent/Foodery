package com.denisardent.network

interface DishesNetworkRepository {
    suspend fun getDishesList(): List<DishResponse>
}