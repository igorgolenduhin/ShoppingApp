package com.example.shoppinglist.feauture_shopping.presentation.add_edit_item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.feauture_shopping.domain.model.InvalidShoppingItemException
import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.domain.use_cases.ShoppingUseCasesHub
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditItemViewModel @Inject constructor(
    private val shoppingUseCasesHub: ShoppingUseCasesHub,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _itemName = mutableStateOf(ItemTextFieldState(hint = "Enter the Item"))
    val itemTitle: State<ItemTextFieldState> = _itemName

    private val _itemColor = mutableStateOf(ShoppingItem.colorForItem.random().toArgb())
    val itemColor: State<Int> = _itemColor

    private val _itemType = mutableStateOf(ShoppingItem.typesOfItem[0])
    val itemType: State<String> = _itemType

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentItemId: Int? = null

    init {
        savedStateHandle.get<Int>("itemId")?.let { itemId ->
            if (itemId != -1) {
                viewModelScope.launch {
                    shoppingUseCasesHub.getShoppingItemByIdUseCase(itemId)?.also { item ->
                        currentItemId = item.id
                        _itemName.value = itemTitle.value.copy(
                            text = item.name,
                            isHintVisible = false
                        )
                        _itemType.value = item.itemType
                        _itemColor.value = item.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditItemEvent) {
        when (event) {
            is AddEditItemEvent.EnteredName -> {
                _itemName.value = itemTitle.value.copy(
                    text = event.name
                )
            }
            is AddEditItemEvent.ChangeTitleFocus -> {
                _itemName.value = itemTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused
                            && itemTitle.value.text.isBlank()
                )
            }
            is AddEditItemEvent.ChangeColor -> {
                _itemColor.value = event.color
            }
            is AddEditItemEvent.ChangeItemType -> {
                _itemType.value = event.itemType
            }
            is AddEditItemEvent.SaveItem -> {
                viewModelScope.launch {
                    try {
                        shoppingUseCasesHub.addShoppingItemUseCase(
                            ShoppingItem(
                                id = currentItemId,
                                name = itemTitle.value.text,
                                color = itemColor.value,
                                timestamp = System.currentTimeMillis(),
                                itemType = itemType.value,
                                completion = false
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveItem)
                    } catch (e: InvalidShoppingItemException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Could not save your item"
                            )
                        )
                    }
                }
            }
        }
    }
}