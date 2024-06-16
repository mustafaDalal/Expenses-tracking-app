package com.example.financepal.di

import com.example.financepal.di.subcomonents.ExpensesViewmodelSubcomponent
import com.example.financepal.domain.repositories.ExpensesRepository
import com.example.financepal.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ExpensesLocalDatabaseModule::class,
        ExpensesRepositoryModule::class,
        UseCasesModule::class
    ])
interface MainComponent {
    fun expensesSubcomponent() : ExpensesViewmodelSubcomponent.Factory
}