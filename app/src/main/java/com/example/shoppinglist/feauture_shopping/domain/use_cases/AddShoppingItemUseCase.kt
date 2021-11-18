package com.example.shoppinglist.feauture_shopping.domain.use_cases

import com.example.shoppinglist.feauture_shopping.domain.model.InvalidShoppingItemException
import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.domain.repository.ShoppingRepository

class AddShoppingItemUseCase(
    private val repository: ShoppingRepository
) {
    @Throws(InvalidShoppingItemException::class)
    suspend operator fun invoke(shoppingItem: ShoppingItem){
        if(shoppingItem.name.isBlank()){
            throw InvalidShoppingItemException("Please add your item and try again!")
        }
        repository.insertShoppingItem(shoppingItem)
    }
}