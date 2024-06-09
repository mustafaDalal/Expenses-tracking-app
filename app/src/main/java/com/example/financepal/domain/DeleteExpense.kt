package com.example.financepal.domain

import com.example.financepal.domain.repositories.ExpensesRepository

class DeleteExpense(private val repository: ExpensesRepository) {

    suspend fun execute(amount : Int, timestamp : Long) {

        repository.deleteExpense(amount, timestamp)
    }
}