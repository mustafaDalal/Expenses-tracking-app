package com.example.financepal.domain

import com.example.financepal.data.db.Transaction
import com.example.financepal.domain.repositories.ExpensesRepository

class GetExpenses(private val repository: ExpensesRepository) {

    suspend fun execute(): List<Transaction>? {
        return repository.getTotalExpenses()
    }
}