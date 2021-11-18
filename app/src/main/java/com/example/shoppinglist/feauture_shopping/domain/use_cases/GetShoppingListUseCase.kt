package com.example.shoppinglist.feauture_shopping.domain.use_cases

import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.domain.repository.ShoppingRepository
import com.example.shoppinglist.feauture_shopping.domain.util.OrderType
import com.example.shoppinglist.feauture_shopping.domain.util.ShoppingListOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetShoppingListUseCase(
    private val repository: ShoppingRepository
) {
    operator fun invoke(
        shoppingListOrder: ShoppingListOrder = ShoppingListOrder.Date(OrderType.Descending)
    ): Flow<List<ShoppingItem>>
    {
        return repository.getShoppingList().map { items->
            when(shoppingListOrder.orderType){
                is OrderType.Ascending->{
                    when(shoppingListOrder){
                        is ShoppingListOrder.Title -> items.sortedBy { it.name.lowercase() }
                        is ShoppingListOrder.Date -> items.sortedBy { it.timestamp }
                        is ShoppingListOrder.Type -> items.sortedBy { it.itemType }
                    }
                }
                is OrderType.Descending->{
                    when(shoppingListOrder){
                        is ShoppingListOrder.Title -> items.sortedByDescending { it.name.lowercase() }
                        is ShoppingListOrder.Date -> items.sortedByDescending { it.timestamp }
                        is ShoppingListOrder.Type -> items.sortedByDescending { it.itemType }
                    }
                }
            }

        }
    }
}