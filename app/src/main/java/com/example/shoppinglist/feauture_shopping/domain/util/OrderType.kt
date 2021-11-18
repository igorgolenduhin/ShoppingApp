package com.example.shoppinglist.feauture_shopping.domain.util

sealed class OrderType{
    object Ascending : OrderType()
    object Descending : OrderType()
}
