package com.denisardent.foodery.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.domain.usecases.IsSignedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val isSignedInUseCase: IsSignedInUseCase): ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() {
        delay(1000)
        val data = isSignedInUseCase()
        _state.value = State(false, data)
    }
}

data class State(
    var isLoading: Boolean,
    var isSignedIn: Boolean
)