package com.example.shoppinglist.feauture_shopping.presentation.shopping_list

import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.domain.util.OrderType
import com.example.shoppinglist.feauture_shopping.domain.util.ShoppingListOrder

data class ShoppingListState(
    val shoppingList: List<ShoppingItem> = emptyList(),
    val shoppingListOrder: ShoppingListOrder = ShoppingListOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
