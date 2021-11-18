package com.example.shoppinglist.feauture_shopping.presentation.shopping_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.domain.use_cases.ShoppingUseCasesHub
import com.example.shoppinglist.feauture_shopping.domain.util.OrderType
import com.example.shoppinglist.feauture_shopping.domain.util.ShoppingListOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingListUseCasesHub: ShoppingUseCasesHub
) : ViewModel(){
    private val _state = mutableStateOf(ShoppingListState())
    val state: State<ShoppingListState> = _state

    private var recentlyDeletedShoppingItem : ShoppingItem? = null
    private var getShoppingJob : Job? = null

    init {
        getShoppingList(ShoppingListOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: ShoppingListEvent){
        when(event){
            is ShoppingListEvent.Order->{
                if (state.value.shoppingListOrder::class == event.shoppingListOrder::class &&
                        state.value.shoppingListOrder.orderType == event.shoppingListOrder.orderType){
                    return
                }
                getShoppingList(event.shoppingListOrder)
            }
            is ShoppingListEvent.DeleteItem->{
                viewModelScope.launch {
                    shoppingListUseCasesHub.deleteShoppingItemUseCase(event.shoppingItem)
                    recentlyDeletedShoppingItem = event.shoppingItem
                }
            }
            is ShoppingListEvent.RestoreShoppingItem->{
                viewModelScope.launch {
                    shoppingListUseCasesHub.addShoppingItemUseCase(recentlyDeletedShoppingItem ?: return@launch)
                    recentlyDeletedShoppingItem = null
                }
            }
            is ShoppingListEvent.ToggleOrderSection->{
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is ShoppingListEvent.CompleteItem->{
                viewModelScope.launch {
                    shoppingListUseCasesHub.completeShoppingItemUseCase(id = event.id, status = !event.status)
                }
            }
        }
    }

    private fun getShoppingList(shoppingListOrder: ShoppingListOrder) {
        getShoppingJob?.cancel()
        getShoppingJob = shoppingListUseCasesHub.getShoppingListUseCase(shoppingListOrder)
            .onEach { items->
                _state.value = state.value.copy(
                    shoppingList = items,
                    shoppingListOrder = shoppingListOrder
                )
            }
            .launchIn(viewModelScope)
    }
}