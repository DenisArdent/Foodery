package com.denisardent.local.dishes.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DishesDao {

    @Query("SELECT * FROM dishes WHERE account_id = :accountId")
    fun getDishes(accountId: Long): Flow<List<DishDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun changeDishesQuantity(dishDbEntity: DishDbEntity)

    @Query("DELETE FROM dishes WHERE account_id = :accountId AND dish_id = :dishId")
    fun deleteDishDbEntity(accountId: Long, dishId: Long)

    @Query("SELECT quantity FROM dishes WHERE account_id = :accountId AND dish_id = :dishId")
    fun getDishesQuantity(accountId: Long, dishId: Long): Int
}