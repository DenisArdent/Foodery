package com.denisardent.local.restaurants.room

import com.denisardent.common.entities.Restaurant
import com.denisardent.local.restaurants.RestaurantsDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRestaurantsDataRepository @Inject constructor(
    private val restaurantsDao: RestaurantsDao
    ): RestaurantsDataRepository {

    override suspend fun getRestaurants(accountId: Long): List<Restaurant> {



        return withContext(Dispatchers.IO){
            delay(1000)
            restaurantsDao.getRestaurants().map { entity ->
                Restaurant(
                    id = entity.id,
                    name = entity.name,
                    rating = entity.rating,
                    foodType = entity.foodType,
                    deliveryTime = entity.deliveryTime,
                    discountPercentage = entity.discountPercentage,
                    restaurantLogo = RestaurantsDataRepository.LOGOS[entity.id.toInt()-1],
                    isLiked = false
                )
            }
        }
    }

    override suspend fun changeRestaurantState(accountId: Long, restaurantId: Long, isLiked: Boolean){
        /*
        return withContext(Dispatchers.IO){
            restaurantsDao.getRestaurantsState(accountId).map {
                restaurantsLikedTuple ->
                val likedDbEntity = restaurantsLikedTuple.likedDbEntity
                val restaurantDbEntity = restaurantsLikedTuple.restaurantDbEntity
                Restaurant(
                    id = restaurantDbEntity.id,
                    name = restaurantDbEntity.name,
                    rating = restaurantDbEntity.rating,
                    foodType = restaurantDbEntity.foodType,
                    deliveryTime = restaurantDbEntity.deliveryTime,
                    discountPercentage = restaurantDbEntity.discountPercentage,
                    restaurantLogo = RestaurantsDataRepository.LOGOS[restaurantDbEntity.id.toInt() - 1],
                    isLiked = likedDbEntity.isLiked
                )
            }
        }
*/
        withContext(Dispatchers.IO){
            try {
                restaurantsDao.changeRestaurantIsLikedState(
                    accountRestaurantLikedDbEntity = AccountRestaurantLikedDbEntity(
                        accountId,
                        restaurantId,
                        isLiked
                    )
                )
            } catch (e: Exception){
                throw e
            }
        }


    }



    override fun getSelectedRestaurantState(accountId: Long, restaurantId: Long): Boolean {
        val restaurantLikedTuple = restaurantsDao.getRestaurantsState(accountId = accountId).first{
            it.restaurantDbEntity.id == restaurantId
        }
        return restaurantLikedTuple.likedDbEntity.isLiked
    }

}