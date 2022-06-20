package com.example.shoppinglist.feauture_shopping.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shoppinglist.feauture_shopping.presentation.add_edit_item.AddEditItemScreen
import com.example.shoppinglist.feauture_shopping.presentation.shopping_list.ShoppingListScreen
import com.example.shoppinglist.feauture_shopping.presentation.util.Screen
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ShoppingListScreen.route
                    ) {
                        composable(
                            route = Screen.ShoppingListScreen.route
                        ) {
                            ShoppingListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditItemScreen.route + "?itemId={itemId}&itemColor={itemColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "itemId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "itemColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val color = it.arguments?.getInt("itemColor") ?: -1
                            AddEditItemScreen(
                                navController = navController,
                                itemColor = color
                            )
                        }
                    }
                }
            }
        }
    }
}