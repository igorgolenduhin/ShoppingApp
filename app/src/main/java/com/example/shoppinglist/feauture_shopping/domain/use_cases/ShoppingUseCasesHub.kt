package com.example.shoppinglist.feauture_shopping.domain.use_cases

data class ShoppingUseCasesHub(
    val getShoppingListUseCase: GetShoppingListUseCase,
    val getShoppingItemByIdUseCase: GetShoppingItemByIdUseCase,
    val deleteShoppingItemUseCase: DeleteShoppingItemUseCase,
    val addShoppingItemUseCase: AddShoppingItemUseCase,
    val completeShoppingItemUseCase: CompleteShoppingItemUseCase
)