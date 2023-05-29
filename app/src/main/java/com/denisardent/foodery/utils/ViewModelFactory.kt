package com.denisardent.foodery.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisardent.foodery.App
import com.denisardent.foodery.MainViewModel
import com.denisardent.foodery.authorization.SignInViewModel
import com.denisardent.foodery.tabs.views.home.HomeViewModel
import com.denisardent.foodery.tabs.views.infoprofile.InfoProfileViewModel

class ViewModelFactory(
    private val app: App
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            HomeViewModel::class.java -> HomeViewModel(app.restaurantRepository)
            SignInViewModel::class.java -> SignInViewModel(app.accountsRepository)
            MainViewModel::class.java -> MainViewModel(app.accountsRepository)
            InfoProfileViewModel::class.java -> InfoProfileViewModel(app.accountsRepository)
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}