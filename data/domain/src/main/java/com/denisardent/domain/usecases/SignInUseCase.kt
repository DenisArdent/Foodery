package com.denisardent.domain.usecases

import com.denisardent.local.accounts.AccountsDataRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository
) {

    suspend operator fun invoke(
        email: String, password: String
    ){
        accountsDataRepository.signIn(email, password)
    }
}