package com.example.financepal.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.financepal.data.repository.BudgetRepositoryImpl
import com.example.financepal.domain.repositories.BudgetRepository
import com.example.financepal.presentation.MainApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BudgetRepositoryModule {
    @Provides
    @Singleton
    fun providesBudgetRepository(app: Application) : BudgetRepository{

        val sharedPreferences = app.applicationContext.getSharedPreferences("budget", Context.MODE_PRIVATE)
        return BudgetRepositoryImpl(sharedPreferences)
    }

}