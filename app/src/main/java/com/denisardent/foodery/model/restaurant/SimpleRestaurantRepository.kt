package com.denisardent.foodery.model.restaurant

import com.denisardent.foodery.R
import kotlinx.coroutines.delay
import kotlin.random.Random

class SimpleRestaurantRepository: RestaurantRepository {

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

    private val restaurantList = List(5) { index ->
            Restaurant(
                name = types[index] + " " + names[index],
                deliveryTime = listOf(15, 20, 25 ,30,40).random(),
                foodType = types[index],
                rating = Random.nextDouble(3.0, 5.0).format(1),
                restaurantLogo = logos[index],
                discountPercentage = listOf(15,20,30, 35, 40, 50).random()
            )
    }


    override suspend fun getRestaurants(): List<Restaurant>{
        delay(3000)
        return restaurantList
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