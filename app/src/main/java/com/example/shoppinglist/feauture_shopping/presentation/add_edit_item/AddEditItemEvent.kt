package com.example.shoppinglist.feauture_shopping.presentation.add_edit_item

import androidx.compose.ui.focus.FocusState

sealed class AddEditItemEvent{
    data class EnteredName(val name: String) : AddEditItemEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditItemEvent()
    data class ChangeColor(val color: Int) : AddEditItemEvent()
    data class ChangeItemType(val itemType: String) : AddEditItemEvent()
    object SaveItem : AddEditItemEvent()
}
