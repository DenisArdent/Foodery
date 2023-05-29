package com.denisardent.foodery.model.restaurant

import android.database.Cursor
import android.database.sqlite.SQLiteCursor
import android.database.sqlite.SQLiteDatabase
import com.denisardent.foodery.R
import com.denisardent.foodery.preferences.AppPreferences
import com.denisardent.foodery.sqlite.AppSQLiteContract
import com.denisardent.foodery.sqlite.AppSQLiteContract.RestaurantsTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random

class SQLiteRestaurantRepository(
    private val database: SQLiteDatabase
    ): RestaurantRepository {

    /**
     * Restaurants logos list
     */
    private val logos = listOf(R.drawable.ic_first_restaurant, R.drawable.ic_second_restaurant, R.drawable.ic_third_restaurant, R.drawable.ic_fourth_restaurant, R.drawable.foodery)
    /**
     * Restaurants types list
     */
    private val types = listOf("Fish", "Sea", "Delicious", "French", "Italy", "Russian")

    /**
     * Restaurants names list
     */
    private val names = listOf("Food", "Restaurant", "Eatery", "Restaurant", "Food")

    override suspend fun getRestaurants(): List<Restaurant>{
        val restaurants = withContext(Dispatchers.IO){
            queryRestaurants()
        }

        return restaurants
    }

    private fun queryRestaurants(): List<Restaurant>{
        val cursor = database.rawQuery(
            "SELECT * FROM ${RestaurantsTable.TABLE_NAME}",
            null
        )

        return cursor.use {
            val list = mutableListOf<Restaurant>()
            while (cursor.moveToNext()){
                list.add(parseRestaurants(cursor))
            }
            return@use list
        }
    }

    private fun parseRestaurants(cursor: Cursor): Restaurant{
        return Restaurant(
            name = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantsTable.COLUMN_RESTAURANT_NAME)),
            rating = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantsTable.COLUMN_RATING)),
            deliveryTime = cursor.getInt(cursor.getColumnIndexOrThrow(RestaurantsTable.COLUMN_DELIVERY_TIME)),
            foodType = cursor.getString(cursor.getColumnIndexOrThrow(RestaurantsTable.COLUMN_FOOD_TYPE)),
            discountPercentage = cursor.getInt(cursor.getColumnIndexOrThrow(RestaurantsTable.COLUMN_DISCOUNT_PERCENTAGE)),
            restaurantLogo = logos[cursor.getInt(cursor.getColumnIndexOrThrow(RestaurantsTable.COLUMN_ID))-1]
        )
    }
}


/**
 * Extension function that format double value into string with specified accuracy
 *
 * @property scale the scale of returned value
 */

fun Double.format(scale: Int): String{
    return "%.${scale}f".format(this)
}