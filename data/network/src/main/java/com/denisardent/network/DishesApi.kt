package com.denisardent.network

import retrofit2.http.GET

interface DishesApi {
    @GET("v3/e561b1f8-3d4c-47f4-b936-8f896795e188")
    suspend fun getDishes(): List<DishResponse>
}