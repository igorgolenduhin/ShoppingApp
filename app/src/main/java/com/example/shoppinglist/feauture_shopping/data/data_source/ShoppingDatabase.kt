package com.example.shoppinglist.feauture_shopping.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppinglist.feauture_shopping.domain.model.ShoppingItem
@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase(){
    abstract val shoppingDao : ShoppingDao

    companion object{
        const val DATABASE_NAME = "shopping_db"
    }
}