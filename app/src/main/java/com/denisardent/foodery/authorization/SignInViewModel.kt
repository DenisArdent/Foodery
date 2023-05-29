package com.denisardent.foodery.authorization

import android.content.BroadcastReceiver.PendingResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.AuthException
import com.denisardent.foodery.model.accounts.AccountsRepository
import com.denisardent.foodery.tabs.views.LiveResult
import kotlinx.coroutines.launch

class SignInViewModel(private val accountsRepository: AccountsRepository): ViewModel() {
    private val _signInLiveData = MutableLiveData<Boolean>()
    val signInLiveData: LiveData<Boolean> = _signInLiveData


    fun signIn(email: String, password: String){
        viewModelScope.launch{
            try {
                val isSignedIn = accountsRepository.signIn(email, password)
                _signInLiveData.value = isSignedIn
            } catch (e: AuthException){
                _signInLiveData.value = false
            }
        }
    }
}