package com.example.financepal.domain

import com.example.financepal.data.db.Transaction
import com.example.financepal.domain.repositories.ExpensesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetExpenses(private val repository: ExpensesRepository) {

    suspend fun execute(): Flow<List<Transaction>>? = flow {

        repository.getTotalExpenses()?.let { emit(it) }
    }
}