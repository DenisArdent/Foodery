package com.denisardent.foodery.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.denisardent.foodery.App
import com.denisardent.foodery.MainViewModel
import com.denisardent.foodery.authorization.signin.SignInViewModel
import com.denisardent.foodery.authorization.signup.SignUpViewModel
import com.denisardent.foodery.tabs.views.home.HomeViewModel
import com.denisardent.foodery.tabs.views.home.restaurant.RestaurantViewModel
import com.denisardent.foodery.tabs.views.infoprofile.InfoProfileViewModel

class ViewModelFactory(
    private val app: App
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            RestaurantViewModel::class.java -> RestaurantViewModel(app.restaurantRepository, app.accountsRepository)
            HomeViewModel::class.java -> HomeViewModel(app.restaurantRepository, app.accountsRepository)
            SignInViewModel::class.java -> SignInViewModel(app.accountsRepository)
            SignUpViewModel::class.java -> SignUpViewModel(app.accountsRepository)
            MainViewModel::class.java -> MainViewModel(app.accountsRepository)
            InfoProfileViewModel::class.java -> InfoProfileViewModel(app.accountsRepository)
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}