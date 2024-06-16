package com.example.financepal.di

import com.example.financepal.data.dao.ExpensesDao
import com.example.financepal.data.db.ExpensesLocalDatabase
import com.example.financepal.data.db.ExpensesLocalDatabaseImpl
import com.example.financepal.data.repository.ExpensesRepositoryImpl
import com.example.financepal.domain.repositories.ExpensesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExpensesRepositoryModule {
    @Provides
    @Singleton
    fun providesLocalDatabaseImpl(dao: ExpensesDao): ExpensesLocalDatabase {
        return ExpensesLocalDatabaseImpl(dao)
    }
    @Provides
    @Singleton
    fun providesRepository(localDb: ExpensesLocalDatabase): ExpensesRepository {
        return ExpensesRepositoryImpl(localDb)
    }
}