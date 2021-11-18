package com.example.shoppinglist.feauture_shopping.domain.util

sealed class ShoppingListOrder(val orderType: OrderType){
    class Title(orderType: OrderType) : ShoppingListOrder(orderType)
    class Date(orderType: OrderType) : ShoppingListOrder(orderType)
    class Type(orderType: OrderType) : ShoppingListOrder(orderType)

    fun copy(orderType: OrderType) : ShoppingListOrder{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Type -> Type(orderType)
        }
    }
}
