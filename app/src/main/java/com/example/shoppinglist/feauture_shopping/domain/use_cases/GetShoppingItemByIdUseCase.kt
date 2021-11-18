package com.example.shoppinglist.feauture_shopping.domain.use_cases

import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.domain.repository.ShoppingRepository

class GetShoppingItemByIdUseCase(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(id: Int) : ShoppingItem?{
        return repository.getShoppingItemById(id)
    }
}