package com.example.financepal.domain.repositories

interface BudgetRepository {
    suspend fun getBudget() : Int?
    suspend fun updateBudget(newBudget : Int) : Int?
}