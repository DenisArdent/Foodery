package com.denisardent.network.di

import com.denisardent.network.DishesNetworkRepository
import com.denisardent.network.RetrofitDishesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DishesNetworkModule {
    @Binds
    fun bindDishesRepository(dishesRepository: RetrofitDishesRepository): DishesNetworkRepository
}