package com.example.shoppinglist.feauture_shopping.data.data_source

import androidx.room.*
import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {
    @Query("SELECT * FROM shoppingitem")
    fun getShoppingList(): Flow<List<ShoppingItem>>

    @Query("SELECT * FROM shoppingitem WHERE id= :id")
    suspend fun getShoppingItemById(id: Int) : ShoppingItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Delete
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    @Query("UPDATE shoppingitem SET completion = :status WHERE id= :id ")
    suspend fun completeShoppingItem(id: Int, status: Boolean)
}