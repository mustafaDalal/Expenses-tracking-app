package com.example.financepal.domain

import com.example.financepal.domain.repositories.BudgetRepository

class UpdateBudget(private val repository: BudgetRepository) {

    suspend fun execute(newBudget : Int?): Int? {
        return newBudget?.let { repository.updateBudget(it) }
    }
}