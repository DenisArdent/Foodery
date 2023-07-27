package com.denisardent.local.di

import android.app.Application
import androidx.room.Room
import com.denisardent.local.accounts.room.AccountsDao
import com.denisardent.local.dishes.room.DishesDao
import com.denisardent.local.restaurants.room.RestaurantsDao
import com.denisardent.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(appContext: Application): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "roomdatabase.db")
            .createFromAsset("db_init.db")
            .build()
    }

    @Provides
    fun provideAccountsDao(appDatabase: AppDatabase): AccountsDao{
        return appDatabase.getAccountsDao()
    }

    @Provides
    fun provideRestaurantsDao(appDatabase: AppDatabase): RestaurantsDao {
        return appDatabase.getRestaurantsDao()
    }

    @Provides
    fun provideDishesDao(appDatabase: AppDatabase): DishesDao {
        return appDatabase.getDishesDao()
    }
}