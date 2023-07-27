package com.denisardent.domain.usecases

import com.denisardent.local.accounts.AccountsDataRepository
import javax.inject.Inject

class IsSignedInUseCase @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository
) {
    suspend operator fun invoke(): Boolean{
        return accountsDataRepository.isSignedIn()
    }
}