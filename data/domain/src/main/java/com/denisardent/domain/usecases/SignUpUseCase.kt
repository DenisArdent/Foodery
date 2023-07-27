package com.denisardent.domain.usecases

import com.denisardent.common.entities.SignUpData
import com.denisardent.local.accounts.AccountsDataRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository
) {
    suspend operator fun invoke(
        signUpData: SignUpData
    ){
        accountsDataRepository.signUp(signUpData)
    }
}