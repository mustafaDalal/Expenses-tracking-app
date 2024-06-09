package com.example.financepal.domain

import com.example.financepal.domain.repositories.ExpensesRepository

class DeleteAllExpenses(private val repository: ExpensesRepository) {

    suspend fun execute(){
        repository.deleteAllExpenses()
    }
}