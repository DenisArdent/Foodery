package com.denisardent.authorization.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisardent.domain.usecases.SignUpUseCase
import com.denisardent.presentation.MutableResultFlow
import com.denisardent.presentation.ResultFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
    ): ViewModel() {
    private val _signUpState: MutableResultFlow<Boolean> = MutableStateFlow(
        com.denisardent.common.SuccessResult(
            false
        )
    )

    val signUpState: ResultFlow<Boolean> = _signUpState.asStateFlow()


    fun signUp(signUpData: com.denisardent.common.entities.SignUpData){
        viewModelScope.launch{
            try {
                _signUpState.tryEmit(com.denisardent.common.PendingResult())
                signUpUseCase(signUpData)
                _signUpState.tryEmit(com.denisardent.common.SuccessResult(true))
            } catch (e: Exception){
                _signUpState.tryEmit(com.denisardent.common.ErrorResult(e))
            }
        }
    }
}
