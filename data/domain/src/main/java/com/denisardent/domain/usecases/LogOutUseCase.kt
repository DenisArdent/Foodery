package com.denisardent.domain.usecases

import com.denisardent.local.accounts.AccountsDataRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val accountsDataRepository: AccountsDataRepository) {
    operator fun invoke(){
        accountsDataRepository.logOut()
    }
}