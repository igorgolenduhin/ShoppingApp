package com.example.shoppinglist.feauture_shopping.presentation.shopping_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.feauture_shopping.domain.util.OrderType
import com.example.shoppinglist.feauture_shopping.domain.util.ShoppingListOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    shoppingListOrder: ShoppingListOrder = ShoppingListOrder.Date(OrderType.Descending),
    onOrderChange: (ShoppingListOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                selected = shoppingListOrder is ShoppingListOrder.Title,
                onSelect = { onOrderChange(ShoppingListOrder.Title(shoppingListOrder.orderType)) })
            Spacer(modifier = Modifier.width(8.dp))


            DefaultRadioButton(
                text = "Date",
                selected = shoppingListOrder is ShoppingListOrder.Date,
                onSelect = { onOrderChange(ShoppingListOrder.Date(shoppingListOrder.orderType)) })
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Type",
                selected = shoppingListOrder is ShoppingListOrder.Type,
                onSelect = { onOrderChange(ShoppingListOrder.Type(shoppingListOrder.orderType)) })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                selected = shoppingListOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(shoppingListOrder.copy(OrderType.Ascending)) })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = shoppingListOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(shoppingListOrder.copy(OrderType.Descending)) })
        }
    }
}