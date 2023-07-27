package com.denisardent.local.di

import com.denisardent.local.security.SecurityUtils
import com.denisardent.local.security.SecurityUtilsSimpleImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SecurityModule {

    @Binds
    fun bindSecurityUtils(securityUtils: SecurityUtilsSimpleImpl): SecurityUtils
}