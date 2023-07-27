package com.denisardent.domain.usecases

import com.denisardent.local.accounts.AccountsDataRepository
import javax.inject.Inject


class GetCurrentIdUseCase @Inject constructor(
    private val accountsDataRepository: AccountsDataRepository
) {
    operator fun invoke(): Long{
        return accountsDataRepository.getCurrentId()
    }
}