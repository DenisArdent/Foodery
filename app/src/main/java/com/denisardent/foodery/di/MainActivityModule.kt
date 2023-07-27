package com.denisardent.foodery.di

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.denisardent.foodery.R
import com.denisardent.foodery.screens.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class MainActivityModule {

    @Provides
    fun provideNavController(@ActivityContext activity: Context): NavController{
        return (activity as MainActivity).supportFragmentManager.findFragmentById(R.id.fragment_container)!!.findNavController()
    }
}