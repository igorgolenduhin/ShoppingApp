package com.example.shoppinglist.di

import android.app.Application
import androidx.room.Room
import com.example.shoppinglist.feauture_shopping.data.data_source.ShoppingDatabase
import com.example.shoppinglist.feauture_shopping.data.repository.ShoppingRepositoryImpl
import com.example.shoppinglist.feauture_shopping.domain.repository.ShoppingRepository
import com.example.shoppinglist.feauture_shopping.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideShoppingApplication(app:Application) : ShoppingDatabase {
        return Room.databaseBuilder(
            app,
            ShoppingDatabase::class.java,
            ShoppingDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingRepository(database:ShoppingDatabase) : ShoppingRepository {
        return ShoppingRepositoryImpl(database.shoppingDao)
    }

    @Provides
    @Singleton
    fun provideShoppingUseCases(repository: ShoppingRepository) : ShoppingUseCasesHub {
        return ShoppingUseCasesHub(
            getShoppingListUseCase = GetShoppingListUseCase(repository),
            getShoppingItemByIdUseCase = GetShoppingItemByIdUseCase(repository),
            deleteShoppingItemUseCase = DeleteShoppingItemUseCase(repository),
            addShoppingItemUseCase = AddShoppingItemUseCase(repository),
            completeShoppingItemUseCase = CompleteShoppingItemUseCase(repository)
        )
    }
}