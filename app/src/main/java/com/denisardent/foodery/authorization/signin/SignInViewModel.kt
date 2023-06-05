package com.denisardent.foodery.authorization.signin

import android.content.BroadcastReceiver.PendingResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.AuthException
import com.denisardent.foodery.model.accounts.AccountsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(private val accountsRepository: AccountsRepository): ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _signInLiveData = MutableLiveData<Boolean>()
    val signInLiveData: LiveData<Boolean> = _signInLiveData


    fun signIn(email: String, password: String){
        viewModelScope.launch{
            try {
                _isLoading.value = true
                val isSignedIn = accountsRepository.signIn(email, password)
                _signInLiveData.value = isSignedIn
            } catch (e: AuthException){
                _signInLiveData.value = false
            }
            _isLoading.value = false
        }
    }
}

data class SignInState(
    val isLoading: Boolean,
    val isSuccessfully: Boolean
)