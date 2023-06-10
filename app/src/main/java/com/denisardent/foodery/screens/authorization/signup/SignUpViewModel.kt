package com.denisardent.foodery.screens.authorization.signup

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.AuthException
import com.denisardent.foodery.model.ErrorResult
import com.denisardent.foodery.model.PendingResult
import com.denisardent.foodery.model.SuccessResult
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.model.accounts.entities.SignUpData
import com.denisardent.foodery.utils.base.MutableResultFlow
import com.denisardent.foodery.utils.base.ResultFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val accountsRepository: AccountsRepository): ViewModel() {
    private val _signUpState: MutableResultFlow<Boolean> = MutableStateFlow(SuccessResult(false))
    val signUpState: ResultFlow<Boolean> = _signUpState.asStateFlow()


    fun signUp(signUpData: SignUpData){
        viewModelScope.launch{
            try {
                _signUpState.tryEmit(PendingResult())
               accountsRepository.signUp(signUpData)
                _signUpState.tryEmit(SuccessResult(true))
            } catch (e: Exception){
                _signUpState.tryEmit(ErrorResult(e))
            }
        }
    }
}