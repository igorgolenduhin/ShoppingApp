package com.example.shoppinglist.feauture_shopping.presentation.shopping_list

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shoppinglist.feauture_shopping.presentation.shopping_list.components.OrderSection
import com.example.shoppinglist.feauture_shopping.presentation.shopping_list.components.ShoppingItemObject
import com.example.shoppinglist.feauture_shopping.presentation.util.Screen
import kotlinx.coroutines.launch

@Composable
fun ShoppingListScreen(
    navController: NavController,
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditItemScreen.route)
            }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new shopping item"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Shopping List",
                    style = MaterialTheme.typography.h4
                )
                IconButton(onClick = {
                    viewModel.onEvent(ShoppingListEvent.ToggleOrderSection)
                }) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    shoppingListOrder = state.shoppingListOrder,
                    onOrderChange = {
                        viewModel.onEvent(ShoppingListEvent.Order(it))
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.shoppingList) { shoppingItem ->
                    ShoppingItemObject(
                        shoppingItem = shoppingItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditItemScreen.route + "?itemId=${shoppingItem.id}&itemColor=${shoppingItem.color}"
                                )
                            },
                        onDeleteClick = {
                            viewModel.onEvent(ShoppingListEvent.DeleteItem(shoppingItem))
                            scope.launch {
                                val resultOfDeleting = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "${shoppingItem.name} was deleted",
                                    actionLabel = "Undo"
                                )
                                if (resultOfDeleting == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(ShoppingListEvent.RestoreShoppingItem)
                                }
                            }
                        },
                        onCompleteClick = {
                            viewModel.onEvent(
                                ShoppingListEvent.CompleteItem(
                                    id = shoppingItem.id ?: -1,
                                    status = shoppingItem.completion
                                )
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}