package com.denisardent.local.restaurants.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "restaurants"
)
data class RestaurantDbEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val rating: String,
    @ColumnInfo(name = "food_type") val foodType: String,
    @ColumnInfo(name = "delivery_time") val deliveryTime: Int,
    @ColumnInfo(name = "discount_percentage") val discountPercentage: Int
)