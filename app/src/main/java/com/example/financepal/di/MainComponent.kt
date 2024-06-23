package com.example.financepal.di

import com.example.financepal.di.subcomonents.ExpensesViewmodelSubcomponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ExpensesLocalDatabaseModule::class,
        ExpensesRepositoryModule::class,
        BudgetRepositoryModule::class,
        UseCasesModule::class
    ])
interface MainComponent {
    fun expensesSubcomponent() : ExpensesViewmodelSubcomponent.Factory
}