package com.example.financepal.domain

import com.example.financepal.data.db.Transaction
import com.example.financepal.domain.repositories.ExpensesRepository

class UpdateExpenses(private val repository: ExpensesRepository) {

    suspend fun execute(transaction : Transaction) {

        repository.updateTotalExpenses(transaction)
    }
}