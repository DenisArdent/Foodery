package com.denisardent.foodery.model.accounts.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.denisardent.foodery.model.accounts.entities.Account
import com.denisardent.foodery.model.accounts.entities.SignUpData

@Entity(
    tableName = "accounts",
    indices = [
        Index("email", unique = true),
    ]
)

data class AccountDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val email: String,
    val username: String,
    val password: String,
    @ColumnInfo(name = "created_at") val createdAt: Long
) {
    fun mapToAccount(): Account =
        Account(
            id = id,
            email = email,
            username = username,
            createdAt = createdAt,
            phoneNumber = null
        )

    companion object{
        fun mapFromSignUpData(signUpData: SignUpData): AccountDbEntity =
            AccountDbEntity(
                id = 0,
                email = signUpData.email,
                username = signUpData.username,
                password = signUpData.password,
                createdAt = System.currentTimeMillis()
            )
    }
}