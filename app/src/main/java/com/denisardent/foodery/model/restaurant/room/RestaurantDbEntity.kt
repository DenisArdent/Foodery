package com.denisardent.foodery.model.restaurant.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.denisardent.foodery.model.accounts.entities.Account
import com.denisardent.foodery.model.accounts.entities.Restaurant
import com.denisardent.foodery.model.accounts.entities.SignUpData
import com.denisardent.foodery.model.restaurant.RestaurantRepository

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
) {
    companion object{
        fun mapFromRestaurant(restaurantData: Restaurant): RestaurantDbEntity =
            RestaurantDbEntity(
                id = restaurantData.id,
                name = restaurantData.name,
                rating = restaurantData.rating,
                foodType = restaurantData.foodType,
                deliveryTime = restaurantData.deliveryTime,
                discountPercentage = restaurantData.discountPercentage
            )
    }
}