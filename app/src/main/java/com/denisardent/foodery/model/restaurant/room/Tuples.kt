package com.denisardent.foodery.model.restaurant.room

import androidx.room.Embedded
import androidx.room.Relation
import com.denisardent.foodery.model.restaurant.room.views.AccountLikedRestaurantsView

data class RestaurantsLikedTuple(
    @Embedded val likedDbEntity: AccountRestaurantLikedDbEntity,

    @Relation(
        parentColumn = "restaurant_id",
        entityColumn = "id"
    )
    val restaurantDbEntity: RestaurantDbEntity
)
