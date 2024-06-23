package com.example.financepal.di

import android.content.SharedPreferences
import com.example.financepal.data.repository.BudgetRepositoryImpl
import com.example.financepal.domain.repositories.BudgetRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BudgetRepositoryModule {
    @Provides
    @Singleton
    fun providesBudgetRepository(sharedPreferences: SharedPreferences) : BudgetRepository{

        return BudgetRepositoryImpl(sharedPreferences)
    }

}