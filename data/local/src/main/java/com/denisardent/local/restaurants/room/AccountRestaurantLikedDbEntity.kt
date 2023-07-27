package com.denisardent.local.restaurants.room

import androidx.room.*
import com.denisardent.local.accounts.room.AccountDbEntity


@Entity(
    tableName = "accounts_restaurants_liked",
    foreignKeys = [
        ForeignKey(
            AccountDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["account_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            RestaurantDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["restaurant_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["account_id", "restaurant_id"] ,
    indices = [
            Index("restaurant_id")
    ]
)
data class AccountRestaurantLikedDbEntity (
    @ColumnInfo(name = "account_id") val accountId: Long,
    @ColumnInfo(name = "restaurant_id") val restaurantId: Long,
    @ColumnInfo(name = "is_liked") val isLiked: Boolean
)