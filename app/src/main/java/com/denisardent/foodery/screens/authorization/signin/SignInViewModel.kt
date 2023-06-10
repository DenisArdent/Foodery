package com.denisardent.foodery.screens.authorization.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.AuthException
import com.denisardent.foodery.model.ErrorResult
import com.denisardent.foodery.model.PendingResult
import com.denisardent.foodery.model.SuccessResult
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.utils.base.MutableResultFlow
import com.denisardent.foodery.utils.base.ResultFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val accountsRepository: AccountsRepository): ViewModel() {
    private val _signInState: MutableResultFlow<Boolean> = MutableStateFlow(SuccessResult(false))
    val signInState: ResultFlow<Boolean> = _signInState.asStateFlow()

    private val _signInLiveData = MutableLiveData<Boolean>()
    val signInLiveData: LiveData<Boolean> = _signInLiveData


    fun signIn(email: String, password: String){
        viewModelScope.launch{
            try {
                _signInState.tryEmit(PendingResult())
                val isSignedIn = accountsRepository.signIn(email, password)
                _signInState.tryEmit(SuccessResult(true))
            } catch (e: AuthException){
                _signInState.tryEmit(ErrorResult(e))
            }
        }
    }
}