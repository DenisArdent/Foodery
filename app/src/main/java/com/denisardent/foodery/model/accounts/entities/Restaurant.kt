package com.denisardent.foodery.model.accounts.entities

/**
 * Data class which defines restaurant's properties.
 */

data class Restaurant(
    val id: Long,
    val name: String,
    val rating: String,
    val foodType: String,
    val deliveryTime: Int,
    val restaurantLogo: Int,
    val discountPercentage: Int,
    val isLiked: Boolean
)
