package com.example.shoppinglist.feauture_shopping.presentation.shopping_list

import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.domain.util.ShoppingListOrder

sealed class ShoppingListEvent{
    data class Order(val shoppingListOrder: ShoppingListOrder) : ShoppingListEvent()
    data class DeleteItem(val shoppingItem: ShoppingItem) : ShoppingListEvent()
    data class CompleteItem(val id: Int, val status: Boolean) : ShoppingListEvent()
    object RestoreShoppingItem : ShoppingListEvent()
    object ToggleOrderSection : ShoppingListEvent()
}
