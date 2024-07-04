package com.example.financepal.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.financepal.data.dao.ExpensesDao
import com.example.financepal.data.db.ExpensesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ExpensesLocalDatabaseModule() {
    @Singleton
    @Provides
    fun providesLocalDatabase(app : Application) : ExpensesDatabase{
        return Room
            .databaseBuilder(context = app.applicationContext, ExpensesDatabase::class.java, "expense_db")
            .build()
    }

    @Singleton
    @Provides
    fun providesExpensesDao(database: ExpensesDatabase) : ExpensesDao{

        return database.expensesDao()
    }
}