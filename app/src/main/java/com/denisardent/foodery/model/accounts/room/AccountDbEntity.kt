package com.denisardent.foodery.model.accounts.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.denisardent.foodery.model.accounts.entities.Account
import com.denisardent.foodery.model.accounts.entities.SignUpData
import com.denisardent.foodery.utils.security.SecurityUtils

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
    val hash: String,
    @ColumnInfo(defaultValue = "") val salt: String,
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
        fun mapFromSignUpData(signUpData: SignUpData, securityUtils: SecurityUtils): AccountDbEntity{
            val saltBytes = securityUtils.generateSalt()
            val hashBytes = securityUtils.passwordToHash(signUpData.password.toCharArray(), saltBytes)
            signUpData.password = "*"
            return AccountDbEntity(
                id = 0,
                email = signUpData.email,
                username = signUpData.username,
                hash = securityUtils.bytesToString(hashBytes),
                salt = securityUtils.bytesToString(saltBytes),
                createdAt = System.currentTimeMillis()
            )
        }
    }
}