package com.denisardent.foodery.authorization.signup

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.AuthException
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.model.accounts.entities.SignUpData
import kotlinx.coroutines.launch

class SignUpViewModel(private val accountsRepository: AccountsRepository): ViewModel() {
    private val _signUpLiveData = MutableLiveData<Boolean>()
    val signUpLiveData: LiveData<Boolean> = _signUpLiveData


    fun signUp(signUpData: SignUpData){
        viewModelScope.launch{
            try {
               accountsRepository.signUp(signUpData)
                _signUpLiveData.value = true
            } catch (e: Exception){
                _signUpLiveData.value = false
            }
        }
    }
}