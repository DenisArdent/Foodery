package com.denisardent.network

import retrofit2.http.GET

interface DishesApi {
    @GET("0b1e1a6d-3bda-42d7-a0ea-402aeaf79906")
    suspend fun getDishes(): List<DishResponse>
}