package com.example.financepal.di

import com.example.financepal.domain.*
import com.example.financepal.domain.repositories.BudgetRepository
import com.example.financepal.domain.repositories.ExpensesRepository
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun providesGetExpensesUseCase(repository: ExpensesRepository) : GetExpenses{
        return GetExpenses(repository)
    }

    @Provides
    fun providesUpdateExpensesUseCase(repository: ExpensesRepository) : UpdateExpenses{

        return UpdateExpenses(repository)
    }

    @Provides
    fun providesDeleteExpenseUseCase(repository: ExpensesRepository) : DeleteExpense{

        return DeleteExpense(repository)
    }

    @Provides
    fun providesDeleteAllExpensesUseCase(repository: ExpensesRepository) : DeleteAllExpenses{

        return DeleteAllExpenses(repository)
    }

    @Provides
    fun providesUpdateBudgetUseCase(repository: BudgetRepository) : UpdateBudget{
        return UpdateBudget(repository)
    }
    @Provides
    fun providesGetBudgetUseCase(repository: BudgetRepository) : GetBudget{
        return GetBudget(repository)
    }

}