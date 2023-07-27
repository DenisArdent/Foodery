package com.denisardent.local.dishes.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.denisardent.local.accounts.room.AccountDbEntity


@Entity(
    tableName = "dishes",
    foreignKeys = [
        ForeignKey(
            AccountDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["account_id", "dish_id"] ,
    indices = [
        Index("dish_id")
    ]
)
data class DishDbEntity (
    @ColumnInfo(name = "account_id") val accountId: Long,
    @ColumnInfo(name = "dish_id") val dishId: Long,
    @ColumnInfo(name = "quantity") val quantity: Int
)
