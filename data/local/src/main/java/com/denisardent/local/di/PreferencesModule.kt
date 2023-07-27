package com.denisardent.local.di

import com.denisardent.local.preferences.AppPreferences
import com.denisardent.local.preferences.AppSharedPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PreferencesModule {

    @Singleton
    @Binds
    fun bindAppPreferences(appSharedPreferences: AppSharedPreferences): AppPreferences
}