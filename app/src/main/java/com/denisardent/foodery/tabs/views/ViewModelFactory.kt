package com.denisardent.foodery.tabs.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisardent.foodery.App
import com.denisardent.foodery.tabs.views.home.HomeViewModel

class ViewModelFactory(
    private val app: App
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            HomeViewModel::class.java -> HomeViewModel(app.restaurantRepository)
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}