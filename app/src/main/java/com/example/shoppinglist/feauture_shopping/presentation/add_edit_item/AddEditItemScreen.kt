package com.example.shoppinglist.feauture_shopping.presentation.add_edit_item

import android.widget.ImageButton
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
import com.example.shoppinglist.feauture_shopping.presentation.add_edit_item.components.CustomTextField
import com.example.shoppinglist.feauture_shopping.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditItemScreen(
    navController: NavController,
    itemColor: Int,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {
    val titelState = viewModel.itemTitle.value
    val scaffoldState = rememberScaffoldState()
    val itemBackgroundAnimatable = remember {
        Animatable(
            Color(if (itemColor != -1) itemColor else viewModel.itemColor.value)
        )
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.SaveItem -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditItemEvent.SaveItem)
                },
                backgroundColor = Color.Gray
            ) {
                Icon(imageVector = Icons.Default.NoteAdd, contentDescription = "Save Item")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(itemBackgroundAnimatable.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ShoppingItem.colorForItem.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(modifier = Modifier
                        .size(50.dp)
                        .shadow(15.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = 3.dp,
                            color = if (viewModel.itemColor.value == colorInt) {
                                Color.Black
                            } else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable {
                            scope.launch {
                                itemBackgroundAnimatable.animateTo(
                                    targetValue = Color(colorInt),
                                    animationSpec = tween(durationMillis = 500)
                                )
                            }
                            viewModel.onEvent(AddEditItemEvent.ChangeColor(colorInt))
                        }
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ShoppingItem.typesOfItem.forEach { type ->
                    when (type) {
                        "Grocery" -> {
                            Icon(
                                imageVector = Icons.Default.LocalGroceryStore,
                                contentDescription = "Grocery Item",
                                tint = if (viewModel.itemType.value == type) {
                                    MaterialTheme.colors.background
                                } else MaterialTheme.colors.primary,

                                modifier = Modifier
                                    .size(if (viewModel.itemType.value == type) {
                                        40.dp
                                    } else 30.dp)
                                    .clickable {
                                        viewModel.onEvent(AddEditItemEvent.ChangeItemType(type))
                                    }
                            )
                            // TODO: 11/17/2021 Solve problem with image for item

                        }
                        "Tech" -> {
                            Icon(
                                imageVector = Icons.Default.Biotech,
                                contentDescription = "Grocery Type",
                                tint = if (viewModel.itemType.value == type) {
                                    MaterialTheme.colors.background
                                } else MaterialTheme.colors.primary,

                                modifier = Modifier
                                    .size(if (viewModel.itemType.value == type) {
                                        40.dp
                                    } else 30.dp)
                                    .clickable {
                                        viewModel.onEvent(AddEditItemEvent.ChangeItemType(type))
                                    }
                            )
                        }
                        "Cloth" -> {
                            Icon(
                                imageVector = Icons.Default.Skateboarding,
                                contentDescription = "Grocery Type",
                                tint = if (viewModel.itemType.value == type) {
                                    MaterialTheme.colors.background
                                } else MaterialTheme.colors.primary,

                                modifier = Modifier
                                    .size(
                                        if (viewModel.itemType.value == type) {
                                            40.dp
                                        } else 30.dp
                                    )
                                    .clickable {
                                        viewModel.onEvent(AddEditItemEvent.ChangeItemType(type))
                                    }
                            )
                        }
                        "Office" -> {
                            Icon(
                                imageVector = Icons.Default.Book,
                                contentDescription = "Grocery Type",
                                tint = if (viewModel.itemType.value == type) {
                                    MaterialTheme.colors.background
                                } else MaterialTheme.colors.primary,

                                modifier = Modifier
                                    .size(
                                        if (viewModel.itemType.value == type) {
                                            40.dp
                                        } else 30.dp
                                    )
                                    .clickable {
                                        viewModel.onEvent(AddEditItemEvent.ChangeItemType(type))
                                    }
                            )
                        }
                    }

                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                text = titelState.text,
                hint = titelState.hint,
                isHintVisible = titelState.isHintVisible,
                onValueChange = {
                    viewModel.onEvent(AddEditItemEvent.EnteredName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditItemEvent.ChangeTitleFocus(it))
                },
                textStyle = MaterialTheme.typography.h5
            )
        }
    }
}