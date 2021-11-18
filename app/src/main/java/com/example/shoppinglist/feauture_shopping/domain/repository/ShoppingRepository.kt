package com.example.shoppinglist.feauture_shopping.domain.repository

import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {
    fun getShoppingList(): Flow<List<ShoppingItem>>
    suspend fun getShoppingItemById(id: Int) : ShoppingItem?
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)
    suspend fun completeShoppingItem(id: Int, status: Boolean)
}