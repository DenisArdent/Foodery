package com.denisardent.foodery.tabs.views.infoprofile

import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.model.restaurant.RestaurantRepository
import com.denisardent.foodery.utils.BaseViewModel

class InfoProfileViewModel(private val accountsRepository: AccountsRepository): BaseViewModel() {

    fun logOut(){
        accountsRepository.logOut()
    }
}