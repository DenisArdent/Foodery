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
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() {
        delay(1000)
        val data = accountsRepository.isSignedIn()
        _state.value = State(false, data)
        Log.d("VM", "${_state.value}")
    }
}

data class State(
    var isLoading: Boolean,
    var isSignedIn: Boolean
)