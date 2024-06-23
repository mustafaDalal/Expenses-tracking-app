package com.example.financepal.di.subcomonents

import com.example.financepal.domain.*
import com.example.financepal.viewmodel.ExpensesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExpensesViewmodelModule {

    @Provides
    @ExpensesScope
    fun providesViewModel(getExpenses : GetExpenses, updateExpenses : UpdateExpenses, deleteAllExpenses: DeleteAllExpenses, updateBudget: UpdateBudget, getBudget: GetBudget): ExpensesViewModelFactory {

        return ExpensesViewModelFactory(getExpenses, updateExpenses, deleteAllExpenses, updateBudget, getBudget)
    }

}