package com.example.financepal.domain.repositories

import com.example.financepal.data.db.Transaction

interface ExpensesRepository {
    suspend fun getTotalExpenses() : List<Transaction>?
    suspend fun updateTotalExpenses(transaction: Transaction)

    suspend fun deleteExpense(amount : Int, timestamp : Long)

    suspend fun deleteAllExpenses()

}