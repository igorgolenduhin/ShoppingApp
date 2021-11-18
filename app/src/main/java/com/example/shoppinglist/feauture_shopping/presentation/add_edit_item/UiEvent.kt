package com.example.shoppinglist.feauture_shopping.presentation.add_edit_item

sealed class UiEvent{
    data class ShowSnackbar(val message: String) : UiEvent()
    object SaveItem : UiEvent()
}
