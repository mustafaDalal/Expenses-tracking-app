package com.example.financepal.domain

import com.example.financepal.domain.repositories.BudgetRepository
import com.example.financepal.domain.repositories.ExpensesRepository

class GetBudget(private val repository: BudgetRepository) {

    suspend fun execute(): Int? {
        return repository.getBudget()
    }
}