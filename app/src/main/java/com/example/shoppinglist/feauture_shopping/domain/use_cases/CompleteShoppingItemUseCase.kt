package com.example.shoppinglist.feauture_shopping.domain.use_cases

import com.example.shoppinglist.feauture_shopping.domain.repository.ShoppingRepository

class CompleteShoppingItemUseCase(
    private val repository: ShoppingRepository
) {
    suspend operator fun invoke(id: Int, status: Boolean){
        return repository.completeShoppingItem(id, status)
    }
}