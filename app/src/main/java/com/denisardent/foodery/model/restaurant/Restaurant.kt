package com.denisardent.foodery.model.restaurant

/**
 * Data class which defines restaurant's properties.
 */

data class Restaurant(
    val name: String,
    val rating: String,
    val foodType: String,
    val deliveryTime: Int,
    val restaurantLogo: Int,
    val discountPercentage: Int
)
