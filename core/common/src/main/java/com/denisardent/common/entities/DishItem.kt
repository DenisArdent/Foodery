package com.denisardent.common.entities

data class DishItem(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val imageUrl: String,
    var quantity: Int = 0
)
