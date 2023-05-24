package com.denisardent.foodery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _isInfoReceived = MutableLiveData<Boolean>(false)
    val isInfoReceived: LiveData<Boolean> = _isInfoReceived

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun  load(){
        delay(3000)
        _isInfoReceived.value = true
    }
}