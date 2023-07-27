@file:Suppress("unused")

package com.denisardent.foodery.di

import com.denisardent.domain.NavRouter
import com.denisardent.foodery.navigation.MainNavRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
interface NavRouterModule {

    @Binds
    fun bindNavRouter(mainNavRouter: MainNavRouter): NavRouter
}
