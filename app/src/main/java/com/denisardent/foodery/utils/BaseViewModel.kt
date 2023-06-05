package com.denisardent.foodery.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.foodery.model.ErrorResult
import com.denisardent.foodery.model.Result
import com.denisardent.foodery.model.SuccessResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

typealias LiveResult<T> = LiveData<Result<T>>
typealias MutableLiveResult<T> = MutableLiveData<Result<T>>
typealias MutableResultFlow<T> = MutableStateFlow<Result<T>>
typealias ResultFlow<T> = StateFlow<Result<T>>

open class BaseViewModel: ViewModel() {

    fun <T> into(liveResult: MutableLiveResult<T>, block: suspend () -> T){
        viewModelScope.launch {
            try {
                liveResult.postValue(SuccessResult(block()))
            } catch (e: Exception){
                liveResult.postValue(ErrorResult(e))
            }
        }
    }
}