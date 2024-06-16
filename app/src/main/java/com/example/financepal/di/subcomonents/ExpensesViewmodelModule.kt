package com.example.financepal.di.subcomonents

import com.example.financepal.domain.GetExpenses
import com.example.financepal.domain.UpdateExpenses
import com.example.financepal.viewmodel.ExpensesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExpensesViewmodelModule {

    @Provides
    @ExpensesScope
    fun providesViewModel(getExpenses : GetExpenses, updateExpenses : UpdateExpenses): ExpensesViewModelFactory {

        return ExpensesViewModelFactory(getExpenses, updateExpenses)
    }

}