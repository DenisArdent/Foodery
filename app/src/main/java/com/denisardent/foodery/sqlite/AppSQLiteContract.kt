package com.denisardent.foodery.sqlite

import android.provider.BaseColumns

object AppSQLiteContract {
    object AccountsTable{
        const val TABLE_NAME = "accounts"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE_NUMBER = "phone_number"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_CREATED_AT = "created_at"
    }

    object RestaurantsTable{
        const val TABLE_NAME = "restaurants"
        const val COLUMN_ID = "id"
        const val COLUMN_RESTAURANT_NAME = "restaurant_name"
        const val COLUMN_RATING = "rating"
        const val COLUMN_FOOD_TYPE = "food_type"
        const val COLUMN_DELIVERY_TIME = "delivery_time"
        const val COLUMN_DISCOUNT_PERCENTAGE = "discount_percentage"
    }

    object AccountsRestaurantsTable{
        const val TABLE_NAME = "accounts_restaurants_favourites"
        const val COLUMN_ACCOUNT_ID = "account_id"
        const val COLUMN_RESTAURANT_ID = "restaurant_id"
        const val COLUMN_IS_FAVOURITE = "is_favourite"
    }
}