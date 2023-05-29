package com.denisardent.foodery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.model.accounts.AccountsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(val accountsRepository: AccountsRepository): ViewModel() {
    private val _isSignedIn = MutableLiveData<State>()
    val isSignedIn: LiveData<State> = _isSignedIn

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() {
        delay(1000)
        val data = accountsRepository.isSignedIn()
        _isSignedIn.value = State(false, data)
        Log.d("VM", "${_isSignedIn.value}")
    }
}

data class State(
    var isReturned: Boolean,
    var isSignedIn: Boolean
)