package com.denisardent.local.restaurants.room

import androidx.room.Embedded
import androidx.room.Relation

data class RestaurantsLikedTuple(
    @Embedded val likedDbEntity: AccountRestaurantLikedDbEntity,

    @Relation(
        parentColumn = "restaurant_id",
        entityColumn = "id"
    )
    val restaurantDbEntity: RestaurantDbEntity
)
