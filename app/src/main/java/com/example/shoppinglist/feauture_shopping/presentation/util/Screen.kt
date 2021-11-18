package com.example.shoppinglist.feauture_shopping.presentation.util

sealed class Screen(val route: String){
    object ShoppingListScreen: Screen("shopping_list_screen")
    object AddEditItemScreen : Screen ("add_edit_item_screen")
}
