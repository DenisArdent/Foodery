package com.denisardent.local.accounts.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.denisardent.common.entities.SignUpData
import com.denisardent.local.security.SecurityUtils

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
    companion object{

        fun mapFromSignUpData(signUpData: SignUpData, securityUtils: SecurityUtils): AccountDbEntity {
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