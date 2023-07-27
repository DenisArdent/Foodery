package com.denisardent.common.entities

data class DishInfo (
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val imageUrl: String,
)