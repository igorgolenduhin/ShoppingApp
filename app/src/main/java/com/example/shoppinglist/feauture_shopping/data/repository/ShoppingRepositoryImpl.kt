package com.example.shoppinglist.feauture_shopping.data.repository

import com.example.shoppinglist.feauture_shopping.data.data_source.ShoppingDao
import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow

class ShoppingRepositoryImpl(private val dao: ShoppingDao) : ShoppingRepository{
    override fun getShoppingList(): Flow<List<ShoppingItem>> {
        return dao.getShoppingList()
    }
    override suspend fun getShoppingItemById(id: Int): ShoppingItem? {
        return dao.getShoppingItemById(id)
    }
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        return dao.insertShoppingItem(shoppingItem)
    }
    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        return dao.deleteShoppingItem(shoppingItem)
    }

    override suspend fun completeShoppingItem(id: Int, status: Boolean) {
        return dao.completeShoppingItem(id, status)
    }


}