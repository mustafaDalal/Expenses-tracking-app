package com.example.financepal.di

import android.content.Context
import androidx.room.Room
import com.example.financepal.data.dao.ExpensesDao
import com.example.financepal.data.db.ExpensesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExpensesLocalDatabaseModule() {
    @Singleton
    @Provides
    fun providesLocalDatabase(context : Context) : ExpensesDatabase{

        return Room
            .databaseBuilder(context = context, ExpensesDatabase::class.java, "expense_db")
            .build()
    }

    @Singleton
    @Provides
    fun providesExpensesDao(database: ExpensesDatabase) : ExpensesDao{

        return database.expensesDao()
    }
}