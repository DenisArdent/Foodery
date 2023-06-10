package com.denisardent.foodery.screens.tabs.views.infoprofile

import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.utils.base.BaseViewModel

class InfoProfileViewModel(private val accountsRepository: AccountsRepository): BaseViewModel() {

    fun logOut(){
        accountsRepository.logOut()
    }
}