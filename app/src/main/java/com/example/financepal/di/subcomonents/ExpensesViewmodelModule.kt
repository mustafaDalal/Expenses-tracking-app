package com.example.financepal.di.subcomonents

import com.example.financepal.domain.*
import com.example.financepal.viewmodel.ExpensesViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ExpensesViewmodelModule {

    @Provides
    @ActivityScoped
    fun providesViewModel(getExpenses : GetExpenses, updateExpenses : UpdateExpenses, deleteAllExpenses: DeleteAllExpenses, updateBudget: UpdateBudget, getBudget: GetBudget): ExpensesViewModelFactory {

        return ExpensesViewModelFactory(getExpenses, updateExpenses, deleteAllExpenses, updateBudget, getBudget)
    }

}