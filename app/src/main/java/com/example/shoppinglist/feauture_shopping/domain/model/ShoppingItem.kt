package com.example.shoppinglist.feauture_shopping.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppinglist.ui.theme.*

@Entity
data class ShoppingItem(
    @PrimaryKey val id : Int? = null,
    val name: String,
    val color: Int,
    val timestamp: Long,
    val itemType: String,
    val completion: Boolean
) {
    companion object {
        val colorForItem = listOf(SweetOrange, SlightYellow, LittleGreen, SkyAzure, StormyBlue)
        val typesOfItem = listOf("Grocery", "Tech", "Cloth", "Office")
    }
}

class InvalidShoppingItemException(message: String) : Exception(message)




