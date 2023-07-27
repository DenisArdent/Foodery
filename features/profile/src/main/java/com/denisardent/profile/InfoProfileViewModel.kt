package com.denisardent.profile

import com.denisardent.domain.usecases.LogOutUseCase
import com.denisardent.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoProfileViewModel @Inject constructor(private val logOutUseCase: LogOutUseCase): BaseViewModel() {

    fun logOut(){
        logOutUseCase()
    }
}
