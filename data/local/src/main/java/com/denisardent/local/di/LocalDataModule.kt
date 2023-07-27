package com.denisardent.local.di

import com.denisardent.local.accounts.AccountsDataRepository
import com.denisardent.local.accounts.room.RoomAccountsDataRepository
import com.denisardent.local.dishes.DishesDataRepository
import com.denisardent.local.dishes.room.RoomDishesDataRepository
import com.denisardent.local.restaurants.RestaurantsDataRepository
import com.denisardent.local.restaurants.room.RoomRestaurantsDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataModule {

    @Binds
    fun bindAccountsRepository(accountsRepository: RoomAccountsDataRepository): AccountsDataRepository

    @Binds
    fun bindRestaurantsRepository(restaurantsRepository: RoomRestaurantsDataRepository): RestaurantsDataRepository

    @Binds
    fun bindDishesRepository(dishesDataRepository: RoomDishesDataRepository): DishesDataRepository
}