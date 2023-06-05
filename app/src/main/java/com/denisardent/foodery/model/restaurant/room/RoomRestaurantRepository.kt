package com.denisardent.foodery.model.restaurant.room

import com.denisardent.foodery.model.accounts.entities.Restaurant
import com.denisardent.foodery.model.restaurant.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomRestaurantRepository(val restaurantsDao: RestaurantsDao): RestaurantRepository {

    override fun getRestaurantsFlow(accountId: Long): Flow<List<Restaurant>> {
        return restaurantsDao.getRestaurantsState(accountId = accountId).map { tuples ->
            tuples.map { restaurantsLikedTuple ->
                val likedDbEntity = restaurantsLikedTuple.likedDbEntity
                val restaurantDbEntity = restaurantsLikedTuple.restaurantDbEntity
                Restaurant(
                    id = restaurantDbEntity.id,
                    name = restaurantDbEntity.name,
                    rating = restaurantDbEntity.rating,
                    foodType = restaurantDbEntity.foodType,
                    deliveryTime = restaurantDbEntity.deliveryTime,
                    discountPercentage = restaurantDbEntity.discountPercentage,
                    restaurantLogo = RestaurantRepository.LOGOS[restaurantDbEntity.id.toInt()-1],
                    isLiked = likedDbEntity.isLiked
                )
            }
        }.flowOn(Dispatchers.IO)


/*        return restaurantsDao.getRestaurants().map {
            delay(1000)
            it.map { entity ->
                Restaurant(
                    id = entity.id,
                    name = entity.name,
                    rating = entity.rating,
                    foodType = entity.foodType,
                    deliveryTime = entity.deliveryTime,
                    discountPercentage = entity.discountPercentage,
                    restaurantLogo = RestaurantRepository.LOGOS[entity.id.toInt()-1],
                    isLiked = null
                )
            }
        }.flowOn(Dispatchers.IO)*/
    }

    override suspend fun changeRestaurantState(accountId: Long, restaurantId: Long, isLiked: Boolean){
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



    override fun getSelectedRestaurantState(accountId: Long, restaurantId: Long): Flow<Boolean> {
        return restaurantsDao.getRestaurantsState(accountId = accountId).map{tuples ->
            val restaurantLikedTuple =  tuples.first {
                it.restaurantDbEntity.id == restaurantId
            }
            restaurantLikedTuple.likedDbEntity.isLiked
        }
    }

}