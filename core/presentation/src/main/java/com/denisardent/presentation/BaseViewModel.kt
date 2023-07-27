package com.denisardent.presentation

import androidx.lifecycle.ViewModel
import com.denisardent.common.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

typealias MutableResultFlow<T> = MutableStateFlow<Result<T>>
typealias ResultFlow<T> = StateFlow<Result<T>>

open class BaseViewModel: ViewModel() {

/*    fun <T> into(liveResult: MutableLiveResult<T>, block: suspend () -> T){
        viewModelScope.launch {
            try {
                liveResult.postValue(SuccessResult(block()))
            } catch (e: Exception){
                liveResult.postValue(ErrorResult(e))
            }
        }
    }*/
}