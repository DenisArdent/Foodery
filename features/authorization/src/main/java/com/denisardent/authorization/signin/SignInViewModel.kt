package com.denisardent.authorization.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.common.AuthException
import com.denisardent.common.ErrorResult
import com.denisardent.common.SuccessResult
import com.denisardent.common.PendingResult
import com.denisardent.domain.usecases.SignInUseCase
import com.denisardent.presentation.MutableResultFlow
import com.denisardent.presentation.ResultFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
    ): ViewModel() {
    private val _signInState: MutableResultFlow<Boolean> = MutableStateFlow(
        SuccessResult(
            false
        )
    )
    val signInState: ResultFlow<Boolean> = _signInState.asStateFlow()

    private val _signInLiveData = MutableLiveData<Boolean>()
    val signInLiveData: LiveData<Boolean> = _signInLiveData


    fun signIn(email: String, password: String){
        viewModelScope.launch{
            try {
                _signInState.tryEmit(PendingResult())
                signInUseCase(email, password)
                _signInState.tryEmit(SuccessResult(true))
            } catch (e: AuthException){
                _signInState.tryEmit(ErrorResult(e))
            }
        }
    }
}
